package com.achmadabrar.movieapp_mandiri.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import kotlinx.coroutines.*

class ListMovieDataSource(
    private val scope: CoroutineScope,
    private val api: TheMovieApiServices,
    private val genre: Genre?
): PageKeyedDataSource<Int, Result>() {

    companion object {
        const val INITIAL_PAGE = 10 * 2
        const val PAGE_SIZE = 10
        const val PREFECTH_DISTANCE = PAGE_SIZE / 2
    }

    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()

    var newList = mutableListOf<Result>()

    private var retryQuery: (() -> Any)? =
        null // Keep reference of the last query (to be able to retry it if necessary)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        retryQuery = { loadInitial(params, callback) }
        loadMovie {
            it.forEach { newResult ->
                newResult.genreId.forEach {
                    if (genre?.id != null) {
                        if (it.toLong() == genre.id) {
                            Log.d("genreId", "$it sama dengan ${genre.id}")
                            newList.add(newResult)
                        }
                    }
                }
            }
            val nextPage =
                if (newList.size < params.requestedLoadSize) null else (INITIAL_PAGE / PAGE_SIZE + 1)
            callback.onResult(newList, null, nextPage)
            if (newList.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        loadMovie {
            it.forEach { newResult ->
                newResult.genreId.forEach {
                    if (genre?.id != null) {
                        if (it.toLong() == genre.id) {
                            Log.d("genreId", "$it sama dengan ${genre.id}")
                        }
                    }
                }
            }
            callback.onResult(newList, page + 1)
            if (newList.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        //
    }

    private fun loadMovie(callback: (List<Result>) -> Unit) {
        scope.launch { getJobErrorHandler() + supervisorJob
            val movies = api.getPopular()

            if (movies.results.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }

            callback(movies.results)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(ListMovieDataSource::class.java.simpleName, "An error happened: $e")
        networkStatusLiveData.postValue(NetworkState.fialed(e.localizedMessage))
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }
}
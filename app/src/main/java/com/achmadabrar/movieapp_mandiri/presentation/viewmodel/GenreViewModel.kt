package com.achmadabrar.movieapp_mandiri.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.movieapp_mandiri.core.base.BaseViewModel
import com.achmadabrar.movieapp_mandiri.data.datasource.ListMovieDataSource
import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val api: TheMovieApiServices
): BaseViewModel<GenreViewModel>() {

    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()
    var genrePageLiveData: MutableLiveData<ResponseGenres> = MutableLiveData()


    init {
        getGenreFromApi()
    }

    fun getGenreFromApi() {
        ioScope.launch { getJobErrorHandler() + supervisorJob
            val genres = api.getGenres()
            genrePageLiveData.postValue(genres)
            if (genres.genres.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(ListMovieDataSource::class.java.simpleName, "An error happened: $e")
        networkStatusLiveData.postValue(NetworkState.fialed(e.localizedMessage))
    }
}

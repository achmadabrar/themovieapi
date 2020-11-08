package com.achmadabrar.movieapp_mandiri.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.movieapp_mandiri.core.base.BaseViewModel
import com.achmadabrar.movieapp_mandiri.data.model.*
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val api: TheMovieApiServices
) : BaseViewModel<MovieViewModel>() {

    var listResultMovie = mutableListOf<Result>()
    var listMovie: MutableLiveData<List<Result>> = MutableLiveData()

    //section for get list genre
    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()
    var genrePageLiveData: MutableLiveData<ResponseGenres> = MutableLiveData()

    //selected movie
    var movieSelectedLiveData: MutableLiveData<Result> = MutableLiveData()

    //review
    var reviewPageLiveData: MutableLiveData<ResponseReview> = MutableLiveData()

    //youtube
    var youtubeLiveData: MutableLiveData<ResponseYoutube> = MutableLiveData()


    init {
        //for get genre
        getGenreFromApi()
    }

    fun getTheListMovie(genre: Genre?) {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
            val movies = api.getPopular()
            movies.results.forEach { newResult ->
                if (newResult.genreId.contains(genre?.id?.toInt())) {
                    listResultMovie.add(newResult)
                    listMovie.postValue(listResultMovie)
                }
            }
            if (listResultMovie.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    fun getGenreFromApi() {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
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
        Log.e(MovieViewModel::class.java.simpleName, "An error happened: $e")
        networkStatusLiveData.postValue(NetworkState.fialed(e.localizedMessage))
    }

    fun getSelectedMovie(result: Result?) {
        movieSelectedLiveData.postValue(result)
    }

    fun getReviewFromApi(movieId: Int?) {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
            val reviews = api.getReview(movieId!!)
            reviewPageLiveData.postValue(reviews)
            if (reviews.results.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

    fun getYoutubeKey(movieId: Int?) {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
            val reviews = api.getYoutubeVideo(movieId!!)
            youtubeLiveData.postValue(reviews)
            if (reviews.results.isEmpty()) {
                networkStatusLiveData.postValue(NetworkState.EMPTY)
            } else {
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }
        }
    }

}
package com.achmadabrar.movieapp_mandiri.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.movieapp_mandiri.core.base.BaseViewModel
import com.achmadabrar.movieapp_mandiri.data.database.GenreDao
import com.achmadabrar.movieapp_mandiri.data.database.MovieDao
import com.achmadabrar.movieapp_mandiri.data.database.ReviewDao
import com.achmadabrar.movieapp_mandiri.data.model.*
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val ONE_HOUR_CACHE = 3600 * 1000
class MovieViewModel @Inject constructor(
    private val api: TheMovieApiServices,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val reviewDao: ReviewDao
) : BaseViewModel<MovieViewModel>() {

    val currentDate = Date(System.currentTimeMillis())

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
        getGenre()
    }

    fun getTheListMovie(genre: Genre?) {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
            val moviesDao = movieDao.getListMovie()
            movieDao.deleteListMovie(currentDate)
            if (moviesDao?.results.isNullOrEmpty()) {
                val movies = api.getPopular()
                movies.results.forEach { newResult ->
                    if (newResult.genreId.contains(genre?.id?.toInt())) {
                        listResultMovie.add(newResult)
                        listMovie.postValue(listResultMovie)
                    }
                }
                val newResponse = ResponsePopular(results = listResultMovie, expiredDate = Date(System.currentTimeMillis() + ONE_HOUR_CACHE))
                val data = movieDao.insertListMovie(newResponse)
                Log.d("movie-dao", "movie yang disimpan ke database : $data")
                if (listResultMovie.isEmpty()) {
                    networkStatusLiveData.postValue(NetworkState.EMPTY)
                } else {
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                }
            } else {
                listMovie.postValue(moviesDao?.results)
            }
        }
    }

    fun getGenre() {
        ioScope.launch {
            getJobErrorHandler() + supervisorJob
            val genreList = genreDao.getListGenre()
            genreDao.deleteListGenre(currentDate)
            if (genreList?.genres.isNullOrEmpty()) {
                val genres = api.getGenres()
                genrePageLiveData.postValue(genres)
                val newResponse = ResponseGenres(genres = genres.genres, expiredDate = Date(System.currentTimeMillis() + ONE_HOUR_CACHE))
                val data = genreDao.insertListGenre(newResponse)
                Log.d("genres-dao", "data yang disimpan di room $data")
                if (genres.genres.isEmpty()) {
                    networkStatusLiveData.postValue(NetworkState.EMPTY)
                } else {
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                }
            } else {
                genrePageLiveData.postValue(genreList)
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
            val reviewsDao = reviewDao.getListReview()
            reviewDao.deleteListReview(currentDate)
            if (reviewsDao?.results.isNullOrEmpty()) {
                val reviews = api.getReview(movieId!!)
                reviewPageLiveData.postValue(reviews)
                val newResponse = ResponseReview(id = reviews.id, page = reviews.page, results = reviews.results, expiredDate = Date(System.currentTimeMillis() + ONE_HOUR_CACHE))
                val data = reviewDao.insertListReview(newResponse)
                Log.d("review-dao", " data review yang kesimpan ke database $data")
                if (reviews.results.isEmpty()) {
                    networkStatusLiveData.postValue(NetworkState.EMPTY)
                } else {
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                }
            } else {
                reviewPageLiveData.postValue(reviewsDao)
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
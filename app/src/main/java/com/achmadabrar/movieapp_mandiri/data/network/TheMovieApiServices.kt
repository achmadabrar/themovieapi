package com.achmadabrar.movieapp_mandiri.data.network

import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.achmadabrar.movieapp_mandiri.data.model.ResponsePopular
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview
import com.achmadabrar.movieapp_mandiri.data.model.ResponseYoutube
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApiServices {

    companion object{
        const val GENRES = "genre/movie/list"
        const val POPULAR = "movie/popular"
        const val REVIEW = "movie/{movie_id}/reviews"
        const val YOUTUBE = "movie/{movie_id}/videos"
    }

    @GET (GENRES)
    suspend fun getGenres(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponseGenres

    @GET(POPULAR)
    suspend fun getPopular(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponsePopular

    @GET(REVIEW)
    suspend fun getReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ) : ResponseReview

    @GET(YOUTUBE)
    suspend fun getYoutubeVideo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ) : ResponseYoutube
}
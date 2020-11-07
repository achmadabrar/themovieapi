package com.achmadabrar.movieapp_mandiri.data.network

import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.achmadabrar.movieapp_mandiri.data.model.ResponsePopular
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieApiServices {

    companion object{
        const val GENRES = "genre/movie/list"
        const val POPULAR = "movie/popular"

    }

    @GET (GENRES)
    suspend fun getGenres(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponseGenres

    @GET(POPULAR)
    suspend fun getPopular(
        @Query("api_key") api_key: String = "b80bdee7c041098a98259a34ec0c4178"
    ): ResponsePopular
}
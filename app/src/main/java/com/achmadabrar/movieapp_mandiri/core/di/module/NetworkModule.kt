package com.achmadabrar.movieapp_mandiri.core.di.module

import android.app.Application
import androidx.multidex.BuildConfig
import com.achmadabrar.movieapp_mandiri.data.database.MovieDatabase
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providePostApi(retrofit: Retrofit): TheMovieApiServices {
        return retrofit.create(TheMovieApiServices::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = MovieDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideGenreDao(movieDatabase: MovieDatabase) = movieDatabase.genreDao()

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()

    @Singleton
    @Provides
    fun provideReviewDao(movieDatabase: MovieDatabase) = movieDatabase.reviewDao()

}
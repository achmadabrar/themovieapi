package com.achmadabrar.movieapp_mandiri.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.achmadabrar.movieapp_mandiri.data.datasource.ListMovieDataSource
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.data.network.TheMovieApiServices
import kotlinx.coroutines.CoroutineScope

class ListMovieFactory(
    private val scope: CoroutineScope,
    private val api: TheMovieApiServices,
    private val genre: Genre?
): DataSource.Factory<Int, Result>() {

    private lateinit var dataSource: ListMovieDataSource
    val dataSourceLiveData: MutableLiveData<ListMovieDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Result> {
        dataSource =
            ListMovieDataSource(
                scope, api, genre
            )
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}
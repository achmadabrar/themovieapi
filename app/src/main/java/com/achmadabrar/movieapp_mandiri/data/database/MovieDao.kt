package com.achmadabrar.movieapp_mandiri.data.database

import androidx.room.*
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResponseMovieConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponsePopular
import java.util.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM popular_list_table")
    suspend fun getListMovie(): ResponsePopular?

    @Query("DELETE FROM popular_list_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListMovie(expiredDate: Date)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovie(listMovie: ResponsePopular)
}
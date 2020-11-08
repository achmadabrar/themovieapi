package com.achmadabrar.movieapp_mandiri.data.database

import androidx.room.*
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResponseGenresConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import java.util.*

@Dao
@TypeConverters(ResponseGenresConverter::class)
interface GenreDao {
    @Query("SELECT * FROM genre_list_table")
    suspend fun getListGenre(): List<ResponseGenres>

    @Query("DELETE FROM genre_list_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListGenre(expiredDate: Date)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListGenre(listShop: List<ResponseGenres>)
}
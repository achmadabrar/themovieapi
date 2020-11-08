package com.achmadabrar.movieapp_mandiri.data.database

import androidx.room.*
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResponseReviewConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview
import java.util.*

@Dao
@TypeConverters(ResponseReviewConverter::class)
interface ReviewDao {
    interface SearchGenreDao {
        @Query("SELECT * FROM popular_list_table")
        suspend fun getListReview(): List<ResponseReview>

        @Query("DELETE FROM genre_list_table WHERE expiredDate < :expiredDate")
        suspend fun deleteListReview(expiredDate: Date)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertListReview(listShop: List<ResponseReview>)
    }
}
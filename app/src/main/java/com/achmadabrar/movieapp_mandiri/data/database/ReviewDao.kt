package com.achmadabrar.movieapp_mandiri.data.database

import androidx.room.*
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResponseReviewConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview
import java.util.*

@Dao
@TypeConverters(ResponseReviewConverter::class)
interface ReviewDao {
    @Query("SELECT * FROM review_user_table")
    suspend fun getListReview(): ResponseReview?

    @Query("DELETE FROM review_user_table WHERE expiredDate < :expiredDate")
    suspend fun deleteListReview(expiredDate: Date)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListReview(listReview: ResponseReview)
}
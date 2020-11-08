package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResultReviewConverter
import java.util.*

@Entity(tableName = "review_user_table")
@TypeConverters(ResultReviewConverter::class)
data class ResponseReview (
    val id: Long,
    val page: Int,
    @PrimaryKey val results: List<ResultReview>,
    val expiredDate: Date?
)
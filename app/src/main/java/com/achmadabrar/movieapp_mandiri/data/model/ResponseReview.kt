package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResultReviewConverter

@Entity(tableName = "review_user_table")
@TypeConverters(ResultReviewConverter::class)
data class ResponseReview (
    @PrimaryKey val id: Long,
    val page: Int,
    val results: List<ResultReview>
)
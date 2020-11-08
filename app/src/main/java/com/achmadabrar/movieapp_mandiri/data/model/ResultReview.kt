package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity

@Entity
data class ResultReview (
    val author: String,
    val content: String,
    val id: String,
    val url: String
)
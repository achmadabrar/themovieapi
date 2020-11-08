package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity

@Entity
data class ResultReview (
    val author: String? = null,
    val content: String? = null,
    val id: String? = null,
    val url: String? = null
)
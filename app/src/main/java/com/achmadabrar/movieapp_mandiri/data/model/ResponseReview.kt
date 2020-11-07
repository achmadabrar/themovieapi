package com.achmadabrar.movieapp_mandiri.data.model

data class ResponseReview (
    val id: Long,
    val page: Int,
    val results: List<ResultReview>
)
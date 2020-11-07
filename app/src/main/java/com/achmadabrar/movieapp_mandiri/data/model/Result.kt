package com.achmadabrar.movieapp_mandiri.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Result (
    @SerializedName("poster_path")
    val posterPath: String,
    val id: Long,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreId: List<Int>,
    val title: String,
    @SerializedName("vote_average")
    val vote: Double,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date
)
package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.GenreIdListConverter
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
@TypeConverters(GenreIdListConverter::class)
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
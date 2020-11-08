package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.GenreListConverter
import java.util.*

@Entity(tableName = "genre_list_table")
@TypeConverters(GenreListConverter::class)
data class ResponseGenres (
    val genres: List<Genre>,
    @PrimaryKey val expiredDate: Date?
)
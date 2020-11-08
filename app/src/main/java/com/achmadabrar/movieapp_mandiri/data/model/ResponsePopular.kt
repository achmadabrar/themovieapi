package com.achmadabrar.movieapp_mandiri.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.ResultListConverter
import java.util.*

@Entity(tableName = "popular_list_table")
@TypeConverters(ResultListConverter::class)
data class ResponsePopular (
    val results: List<Result>,
    @PrimaryKey val expiredDate: Date?
)
package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<Genre>): String {
            val type = object : TypeToken<MutableList<Genre>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<Genre> {
            val type = object : TypeToken<MutableList<Genre>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
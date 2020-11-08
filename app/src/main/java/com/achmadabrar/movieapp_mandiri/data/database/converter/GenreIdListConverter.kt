package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<Int>): String {
            val type = object : TypeToken<MutableList<Int>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<Int> {
            val type = object : TypeToken<MutableList<Int>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
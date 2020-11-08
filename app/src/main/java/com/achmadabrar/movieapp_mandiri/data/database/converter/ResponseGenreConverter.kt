package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResponseGenresConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<ResponseGenres>): String {
            val type = object : TypeToken<MutableList<ResponseGenres>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<ResponseGenres> {
            val type = object : TypeToken<MutableList<ResponseGenres>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
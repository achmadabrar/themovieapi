package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponsePopular
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResponseMovieConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<ResponsePopular>): String {
            val type = object : TypeToken<MutableList<ResponsePopular>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<ResponsePopular> {
            val type = object : TypeToken<MutableList<ResponsePopular>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
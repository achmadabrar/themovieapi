package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResponseReviewConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<ResponseReview>): String {
            val type = object : TypeToken<MutableList<ResponseReview>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<ResponseReview> {
            val type = object : TypeToken<MutableList<ResponseReview>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
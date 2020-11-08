package com.achmadabrar.movieapp_mandiri.data.database.converter

import androidx.room.TypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResultReview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultReviewConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<ResultReview>): String {
            val type = object : TypeToken<MutableList<ResultReview>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<ResultReview> {
            val type = object : TypeToken<MutableList<ResultReview>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}
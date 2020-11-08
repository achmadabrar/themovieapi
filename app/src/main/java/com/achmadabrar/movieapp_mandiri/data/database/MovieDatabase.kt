package com.achmadabrar.movieapp_mandiri.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.achmadabrar.movieapp_mandiri.data.database.converter.DateTypeConverter
import com.achmadabrar.movieapp_mandiri.data.model.ResponseGenres
import com.achmadabrar.movieapp_mandiri.data.model.ResponsePopular
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview

@Database(
    entities = [ResponseGenres::class, ResponsePopular::class, ResponseReview::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class MovieDatabase: RoomDatabase() {
    
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun reviewDao(): ReviewDao
    
    companion object {
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_database"
            ).fallbackToDestructiveMigration().build()
            return instance!!
        }
    }
}
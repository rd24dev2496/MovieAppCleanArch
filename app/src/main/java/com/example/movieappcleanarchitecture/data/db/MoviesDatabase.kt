package com.example.movieappcleanarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappcleanarchitecture.data.db.DATABASE.DATABASE_MOVIE_VERSION
import com.example.movieappcleanarchitecture.data.entities.MovieData

@Database(entities = arrayOf(MovieData::class), version = DATABASE_MOVIE_VERSION,exportSchema = false)
abstract class MoviesDatabase  : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}

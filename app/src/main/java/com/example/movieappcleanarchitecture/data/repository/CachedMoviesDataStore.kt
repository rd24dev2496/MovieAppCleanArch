package com.example.movieappcleanarchitecture.data.repository

import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.MoviesDataStore
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

class CachedMoviesDataStore(private val moviesCache: MoviesCache): MoviesDataStore {


    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return moviesCache.get(movieId)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}
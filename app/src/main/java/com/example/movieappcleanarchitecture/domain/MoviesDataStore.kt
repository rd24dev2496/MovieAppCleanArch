package com.example.movieappcleanarchitecture.domain


import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

interface MoviesDataStore {
    fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>>
    fun getMovies(): Observable<List<MovieEntity>>

}

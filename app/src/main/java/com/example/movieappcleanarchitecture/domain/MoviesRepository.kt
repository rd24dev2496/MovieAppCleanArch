package com.example.movieappcleanarchitecture.domain

import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable


interface MoviesRepository{
    fun getMovies(): Observable<List<MovieEntity>>
    fun getMovie(movieId: Int): Observable<Optional<MovieEntity>>

}
package com.example.movieappcleanarchitecture.domain
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

interface MoviesCache {

    fun clear()
    fun save(movieEntity: MovieEntity)
    fun remove(movieEntity: MovieEntity)
    fun saveAll(movieEntities: List<MovieEntity>)
    fun getAll(): Observable<List<MovieEntity>>
    fun get(movieId: Int): Observable<Optional<MovieEntity>>
    fun isEmpty(): Boolean
}
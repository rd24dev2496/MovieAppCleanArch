package com.example.movieappcleanarchitecture.data.repository

import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

class MemoryMoviesCache : MoviesCache {

    private val movies: LinkedHashMap<Int, MovieEntity> = LinkedHashMap()

    override fun isEmpty(): Boolean {
        return movies.isEmpty()
    }

    override fun remove(movieEntity: MovieEntity) {
        movies.remove(movieEntity.id)
    }

    override fun clear() {
        movies.clear()
    }

    override fun save(movieEntity: MovieEntity) {
        movies[movieEntity.id] = movieEntity
    }

    override fun saveAll(movieEntities: List<MovieEntity>) {
        movieEntities.forEach { movieEntity -> this.movies[movieEntity.id] = movieEntity }
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.just(movies.values.toList())
    }

    override fun get(movieId: Int): Observable<Optional<MovieEntity>> {
        return Observable.just(Optional.of(movies[movieId]))
    }


}

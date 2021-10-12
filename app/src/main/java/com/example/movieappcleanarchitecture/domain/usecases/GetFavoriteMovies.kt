package com.example.movieappcleanarchitecture.domain.usecases

import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.common.Transformer
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import io.reactivex.Observable

class GetFavoriteMovies(transformer: Transformer<List<MovieEntity>>,
                        private val moviesCache: MoviesCache
): UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}
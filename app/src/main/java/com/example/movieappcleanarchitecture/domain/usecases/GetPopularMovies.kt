package com.example.movieappcleanarchitecture.domain.usecases

import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.common.Transformer
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import io.reactivex.Observable

open class GetPopularMovies  constructor(transformer: Transformer<List<MovieEntity>>,
                                         private val moviesRepository: MoviesRepository
) :
                                         UseCase<List<MovieEntity>>(transformer)
{
    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }

}
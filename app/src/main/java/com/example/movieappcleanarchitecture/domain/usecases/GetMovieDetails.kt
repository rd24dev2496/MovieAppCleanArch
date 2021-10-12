package com.example.movieappcleanarchitecture.domain.usecases

import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.common.Transformer
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class GetMovieDetails(
    transformer: Transformer<Optional<MovieEntity>>,
    private val moviesRepository: MoviesRepository
) : UseCase<Optional<MovieEntity>>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    fun getById(movieId: Int): Observable<Optional<MovieEntity>> {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ENTITY] = movieId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Optional<MovieEntity>> {
        val movieId = data?.get(PARAM_MOVIE_ENTITY)
        movieId?.let {
            return moviesRepository.getMovie(it as Int)
        } ?: return Observable.error({ IllegalArgumentException("MovieId must be provided.") })
    }
}
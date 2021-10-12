package com.example.movieappcleanarchitecture.data.repository

import com.example.movieappcleanarchitecture.data.api.Api
import com.example.movieappcleanarchitecture.data.entities.DetailsData
import com.example.movieappcleanarchitecture.data.entities.MovieData
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.MoviesDataStore
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

class RemoteMoviesDataStore (private val api: Api,
                             private val movieDataMapper: Mapper<MovieData, MovieEntity>,
                             private val detailedDataMapper: Mapper<DetailsData, MovieEntity>
):
    MoviesDataStore {
    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return api.getMovieDetails(movieId).flatMap { detailedData ->
            Observable.just(Optional.of(detailedDataMapper.mapFrom(detailedData)))
        }    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map { results->
            results.movies.map { movieDataMapper.mapFrom(it) }
        }
    }
}
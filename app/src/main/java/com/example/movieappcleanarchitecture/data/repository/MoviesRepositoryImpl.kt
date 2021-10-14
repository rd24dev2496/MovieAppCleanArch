package com.example.movieappcleanarchitecture.data.repository

import com.example.movieappcleanarchitecture.data.api.Api
import com.example.movieappcleanarchitecture.data.entities.DetailsData
import com.example.movieappcleanarchitecture.data.entities.MovieData
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.MoviesDataStore
import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.Optional
import io.reactivex.Observable

class MoviesRepositoryImpl (api: Api, private val cache: MoviesCache, movieDataMapper: Mapper<MovieData, MovieEntity>, detailedDataMapper: Mapper<DetailsData, MovieEntity>
) : MoviesRepository {

    private val memoryDataStore: MoviesDataStore
    private val remoteDataStore: MoviesDataStore

    init {
        memoryDataStore = CachedMoviesDataStore(cache)
        remoteDataStore = RemoteMoviesDataStore(api, movieDataMapper, detailedDataMapper)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return if (!cache.isEmpty()) {
            return memoryDataStore.getMovies()
        } else {
            remoteDataStore.getMovies().doOnNext { cache.saveAll(it) }
        }
    }


    override fun getMovie(movieId: Int): Observable<Optional<MovieEntity>> {
        return remoteDataStore.getMovieById(movieId)
    }

}
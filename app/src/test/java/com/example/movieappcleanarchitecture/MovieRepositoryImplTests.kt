package com.example.movieappcleanarchitecture

import com.example.movieappcleanarchitecture.data.api.Api
import com.example.movieappcleanarchitecture.data.api.MovieListResult
import com.example.movieappcleanarchitecture.data.mappers.DetailsDataMovieEntityMapper
import com.example.movieappcleanarchitecture.data.mappers.MovieDataEntityMapper
import com.example.movieappcleanarchitecture.data.repository.MoviesRepositoryImpl
import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.common.DomainTestUtils.Companion.generateMovieEntityList
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MovieRepositoryImplTests {

    private val movieDataMapper = MovieDataEntityMapper()
    private val detailsDataMapper = DetailsDataMovieEntityMapper()
    private lateinit var api: Api
    private lateinit var movieCache: TestMoviesCache
    private lateinit var movieRepository: MoviesRepository

    @Before
    fun before() {
        api = mock(Api::class.java)
        movieCache = TestMoviesCache()
        movieRepository = MoviesRepositoryImpl(api, movieCache, movieDataMapper, detailsDataMapper)
    }

    @Test
    fun testWhenCacheIsNotEmptyGetMoviesReturnsCachedMovies() {

        movieCache.saveAll(generateMovieEntityList())
        movieRepository.getMovies().test()
                .assertComplete()
                .assertValue { movies -> movies.size == 5 }

        verifyZeroInteractions(api)
    }

    @Test
    fun testWhenCacheIsEmptyGetMoviesReturnsMoviesFromApi() {
        val movieListResult = MovieListResult()
        movieListResult.movies = TestsUtils.generateMovieDataList()
        `when`(api.getPopularMovies()).thenReturn(Observable.just(movieListResult))
        movieRepository.getMovies().test()
                .assertComplete()
                .assertValue { movies -> movies.size == 5 }
    }


    @Test
    fun testGetMovieByIdReturnedApiMovie() {
        val detailsData = TestsUtils.generateDetailsData(100)

        `when`(api.getMovieDetails(100)).thenReturn(Observable.just(detailsData))
        movieRepository.getMovie(100).test()
                .assertComplete()
                .assertValue { it.hasValue() && it.value == detailsDataMapper.mapFrom(detailsData) }
    }
}
package com.example.movieappcleanarchitecture.dagger.popular

import com.example.movieappcleanarchitecture.data.mappers.MovieEntityMovieMapper
import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.usecases.GetPopularMovies
import com.example.movieappcleanarchitecture.presentation.common.ASyncTransformer
import com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments.PopularMoviesVMFactory
import dagger.Module
import dagger.Provides
@Module
class PopularMoviesModule {
    @PopularScope
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMovies {
        return GetPopularMovies(ASyncTransformer(), moviesRepository)
    }
    @PopularScope
    @Provides
    fun providePopularMoviesVMFactory(useCase: GetPopularMovies, mapper: MovieEntityMovieMapper)
            : PopularMoviesVMFactory {
        return PopularMoviesVMFactory(useCase, mapper)
    }
}
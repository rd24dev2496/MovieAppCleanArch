package com.example.movieappcleanarchitecture.dagger.details

import com.example.movieappcleanarchitecture.dagger.DI
import com.example.movieappcleanarchitecture.data.mappers.MovieEntityMovieMapper
import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.MoviesRepository
import com.example.movieappcleanarchitecture.domain.usecases.CheckFavoriteStatus
import com.example.movieappcleanarchitecture.domain.usecases.GetMovieDetails
import com.example.movieappcleanarchitecture.domain.usecases.RemoveFavoriteMovie
import com.example.movieappcleanarchitecture.domain.usecases.SaveFavoriteMovie
import com.example.movieappcleanarchitecture.presentation.common.ASyncTransformer
import com.example.movieappcleanarchitecture.presentation.ui.detail.MovieDetailsVMFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieDetailsModule {

    @Provides
    fun provideRemoveFavoriteMovie(@Named(DI.favoritesCache) moviesCache: MoviesCache): RemoveFavoriteMovie {
        return RemoveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideCheckFavoriteStatus(@Named(DI.favoritesCache) moviesCache: MoviesCache): CheckFavoriteStatus {
        return CheckFavoriteStatus(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideSaveFavoriteMovieUseCase(@Named(DI.favoritesCache) moviesCache: MoviesCache): SaveFavoriteMovie {
        return SaveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(getMovieDetails: GetMovieDetails,
                                     saveFavoriteMovie: SaveFavoriteMovie,
                                     removeFavoriteMovie: RemoveFavoriteMovie,
                                     checkFavoriteStatus: CheckFavoriteStatus,
                                     mapper: MovieEntityMovieMapper
    ): MovieDetailsVMFactory {
        return MovieDetailsVMFactory(getMovieDetails, saveFavoriteMovie, removeFavoriteMovie, checkFavoriteStatus, mapper)
    }
}
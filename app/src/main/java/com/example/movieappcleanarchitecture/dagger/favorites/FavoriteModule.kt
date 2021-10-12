package com.example.movieappcleanarchitecture.dagger.favorites

import com.example.movieappcleanarchitecture.dagger.DI
import com.example.movieappcleanarchitecture.data.mappers.MovieEntityMovieMapper
import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.usecases.GetFavoriteMovies
import com.example.movieappcleanarchitecture.presentation.common.ASyncTransformer
import com.example.movieappcleanarchitecture.presentation.ui.favorites.FavoriteMoviesVMFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FavoriteModule {

    @Provides
    fun provideGetFavoriteMovies(@Named(DI.favoritesCache) moviesCache: MoviesCache): GetFavoriteMovies {
        return GetFavoriteMovies(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideFavoriteMoviesVMFactory(getFavoriteMovies: GetFavoriteMovies,
                                       movieEntityMoveMapper: MovieEntityMovieMapper
    ): FavoriteMoviesVMFactory {
        return FavoriteMoviesVMFactory(getFavoriteMovies, movieEntityMoveMapper)
    }
}
package com.example.movieappcleanarchitecture.dagger.application

import com.example.movieappcleanarchitecture.dagger.data.DataModule
import com.example.movieappcleanarchitecture.dagger.details.MovieDetailsModule
import com.example.movieappcleanarchitecture.dagger.details.MovieDetailsSubComponent
import com.example.movieappcleanarchitecture.dagger.favorites.FavoriteModule
import com.example.movieappcleanarchitecture.dagger.favorites.FavoritesSubComponent
import com.example.movieappcleanarchitecture.dagger.network.NetworkModule
import com.example.movieappcleanarchitecture.dagger.popular.PopularMoviesModule
import com.example.movieappcleanarchitecture.dagger.popular.PopularSubComponent
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class)
     ,(DataModule::class)
     ,(AndroidSupportInjectionModule::class)
])

interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
    fun plus(favoriteMoviesModule: FavoriteModule): FavoritesSubComponent
    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent


}

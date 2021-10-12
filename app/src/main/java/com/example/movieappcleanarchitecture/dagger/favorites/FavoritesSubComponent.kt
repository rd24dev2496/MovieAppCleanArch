package com.example.movieappcleanarchitecture.dagger.favorites

import com.example.movieappcleanarchitecture.presentation.ui.favorites.FavoriteMoviesFragment
import dagger.Subcomponent

@FavoritesScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoritesSubComponent {
    fun inject(favoriteMoviesFragment: FavoriteMoviesFragment)
}
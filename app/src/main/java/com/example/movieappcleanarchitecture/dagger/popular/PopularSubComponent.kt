package com.example.movieappcleanarchitecture.dagger.popular

import com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments.MovieListFragment
import dagger.Subcomponent
@PopularScope
@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(popularMoviesFragment: MovieListFragment)
}

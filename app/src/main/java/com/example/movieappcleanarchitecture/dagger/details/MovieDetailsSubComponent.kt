package com.example.movieappcleanarchitecture.dagger.details

import com.example.movieappcleanarchitecture.presentation.ui.detail.MovieDetailsActivity
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}
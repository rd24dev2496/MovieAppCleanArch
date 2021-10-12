package com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments

import com.example.movieappcleanarchitecture.presentation.entities.Movie


data class PopularMoviesViewState(
        var showLoading: Boolean = true,
        var movies: List<Movie>? = null
)

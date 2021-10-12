package com.example.movieappcleanarchitecture.presentation.ui.favorites

import com.example.movieappcleanarchitecture.base.BaseViewModel
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.GetFavoriteMovies
import com.example.movieappcleanarchitecture.presentation.entities.Movie

class FavoriteMoviesViewModel (private val getFavoriteMovies: GetFavoriteMovies,
                               private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {
}
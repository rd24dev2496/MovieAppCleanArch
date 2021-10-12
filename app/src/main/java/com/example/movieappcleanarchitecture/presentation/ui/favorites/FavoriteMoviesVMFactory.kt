package com.example.movieappcleanarchitecture.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.GetFavoriteMovies
import com.example.movieappcleanarchitecture.presentation.entities.Movie

class FavoriteMoviesVMFactory(private val useCase: GetFavoriteMovies,
                              private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(useCase, mapper) as T
    }

}
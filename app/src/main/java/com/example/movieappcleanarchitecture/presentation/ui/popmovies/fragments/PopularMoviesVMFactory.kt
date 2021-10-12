package com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.GetPopularMovies
import com.example.movieappcleanarchitecture.presentation.entities.Movie

class PopularMoviesVMFactory(private val useCase: GetPopularMovies,
                             private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase, mapper) as T
    }

}
package com.example.movieappcleanarchitecture.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.CheckFavoriteStatus
import com.example.movieappcleanarchitecture.domain.usecases.GetMovieDetails
import com.example.movieappcleanarchitecture.domain.usecases.RemoveFavoriteMovie
import com.example.movieappcleanarchitecture.domain.usecases.SaveFavoriteMovie
import com.example.movieappcleanarchitecture.presentation.entities.Movie


class MovieDetailsVMFactory(
        private val getMovieDetails: GetMovieDetails,
        private val saveFavoriteMovie: SaveFavoriteMovie,
        private val removeFavoriteMovie: RemoveFavoriteMovie,
        private val checkFavoriteStatus: CheckFavoriteStatus,
        private val mapper: Mapper<MovieEntity, Movie>) : ViewModelProvider.Factory {

    var movieId: Int = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
                getMovieDetails,
                saveFavoriteMovie,
                removeFavoriteMovie,
                checkFavoriteStatus,
                mapper,
                movieId) as T
    }

}
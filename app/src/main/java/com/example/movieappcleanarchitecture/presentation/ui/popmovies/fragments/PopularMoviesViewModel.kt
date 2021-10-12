package com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments

import androidx.lifecycle.MutableLiveData
import com.example.movieappcleanarchitecture.base.BaseViewModel
import com.example.movieappcleanarchitecture.common.SingleLiveEvent
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.GetPopularMovies
import com.example.movieappcleanarchitecture.presentation.entities.Movie
import javax.inject.Inject

class PopularMoviesViewModel
@Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val movieEntityMovieMapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {
    var viewState: MutableLiveData<PopularMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()
    init {
        viewState.value = PopularMoviesViewState()
    }

    fun getPopularMovies() {
        addDisposable(getPopularMovies.observable()
                .flatMap { movieEntityMovieMapper.observable(it) }
                .subscribe({ movies ->
                    viewState.value?.let {
                        val newState = this.viewState.value?.copy(showLoading = false, movies = movies)
                        this.viewState.value = newState
                        this.errorState.value = null
                    }

                }, {
                    viewState.value = viewState.value?.copy(showLoading = false)
                    errorState.value = it
                }))
    }

}
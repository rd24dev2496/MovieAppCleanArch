package com.example.movieappcleanarchitecture.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.example.movieappcleanarchitecture.base.BaseViewModel
import com.example.movieappcleanarchitecture.common.SingleLiveEvent
import com.example.movieappcleanarchitecture.domain.Mapper
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.usecases.CheckFavoriteStatus
import com.example.movieappcleanarchitecture.domain.usecases.GetMovieDetails
import com.example.movieappcleanarchitecture.domain.usecases.RemoveFavoriteMovie
import com.example.movieappcleanarchitecture.domain.usecases.SaveFavoriteMovie
import com.example.movieappcleanarchitecture.presentation.entities.Movie
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MovieDetailsViewModel (private val getMovieDetails: GetMovieDetails,
                             private val saveFavoriteMovie: SaveFavoriteMovie,
                             private val removeFavoriteMovie: RemoveFavoriteMovie,
                             private val checkFavoriteStatus: CheckFavoriteStatus,
                             private val mapper: Mapper<MovieEntity, Movie>,
                             private val movieId: Int) : BaseViewModel() {
    lateinit var movieEntity: MovieEntity
    var viewState: MutableLiveData<MovieDetailsViewState> = MutableLiveData()
    var favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable> = SingleLiveEvent()

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
    }

    fun getMovieDetails() {
        addDisposable(
                getMovieDetails.getById(movieId)
                        .map {
                            it.value?.let {
                                movieEntity = it
                                mapper.mapFrom(movieEntity)
                            } ?: run {
                                throw Throwable("Something went wrong :(")
                            }
                        }
                        .zipWith(checkFavoriteStatus.check(movieId),
                                BiFunction<Movie, Boolean, Movie> {
                                    movie, isFavorite ->
                                    movie.isFavorite = isFavorite
                                    return@BiFunction movie
                                })
                        .subscribe(
                                {
                                    onMovieDetailsReceived(it) },
                                {
                                    errorState.value = it }
                        )
        )
    }
    private fun onMovieDetailsReceived(movie: Movie) {

        val newViewState = viewState.value?.copy(
                isLoading = false,
                title = movie.originalTitle,
                videos = movie.details?.videos,
                homepage = movie.details?.homepage,
                overview = movie.details?.overview,
                releaseDate = movie.releaseDate,
                votesAverage = movie.voteAverage,
                backdropUrl = movie.backdropPath,
                genres = movie.details?.genres)

        viewState.value = newViewState
        favoriteState.value = movie.isFavorite
    }

    private fun saveFavorite(movieEntity: MovieEntity): Observable<Boolean> {
        return saveFavoriteMovie.save(movieEntity)
    }

    private fun removeFavorite(movieEntity: MovieEntity): Observable<Boolean> {
        return removeFavoriteMovie.remove(movieEntity)
    }
    fun favoriteButtonClicked() {
        addDisposable(checkFavoriteStatus.check(movieId).flatMap {
            when (it) {
                true -> {
                    removeFavorite(movieEntity)
                }
                false -> {
                    saveFavorite(movieEntity)
                }
            }
        }.subscribe({ isFavorite ->
            favoriteState.value = isFavorite
        }, {
            errorState.value = it
        }))
    }
}

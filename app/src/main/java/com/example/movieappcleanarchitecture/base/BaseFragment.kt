package com.example.movieappcleanarchitecture.base

import android.annotation.SuppressLint
import android.app.ActivityOptions
import androidx.fragment.app.Fragment
import com.example.movieappcleanarchitecture.presentation.entities.Movie
import com.example.movieappcleanarchitecture.presentation.ui.detail.MovieDetailsActivity


open class BaseFragment: Fragment() {

    @SuppressLint("NewApi")
    protected fun navigateToMovieDetailsScreen(movie: Movie) {
        var activityOptions: ActivityOptions? = null

        startActivity(
            MovieDetailsActivity.newIntent(
                context!!,
                movie.id,
                movie.posterPath), activityOptions?.toBundle())

        activity?.overridePendingTransition(0, 0)
    }
}
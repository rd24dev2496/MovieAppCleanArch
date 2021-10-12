package com.example.movieappcleanarchitecture.presentation.ui.popmovies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieappcleanarchitecture.R
import com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments.MOVIE_LIST_FRAGMENT_TAG
import com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments.newMovieListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, newMovieListFragment(), MOVIE_LIST_FRAGMENT_TAG)
                        .commitNow()
                title = getString(R.string.popular)
            }

    }
}



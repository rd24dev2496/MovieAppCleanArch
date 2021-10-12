package com.example.movieappcleanarchitecture

import android.app.Activity
import android.app.Application
import com.example.movieappcleanarchitecture.common.Endpoint.THE_MOVIE_URL
import com.example.movieappcleanarchitecture.common.Query.API_KEY_VALUE
import com.example.movieappcleanarchitecture.dagger.application.AppModule
import com.example.movieappcleanarchitecture.dagger.application.DaggerMainComponent
import com.example.movieappcleanarchitecture.dagger.application.MainComponent
import com.example.movieappcleanarchitecture.dagger.data.DataModule
import com.example.movieappcleanarchitecture.dagger.details.MovieDetailsModule
import com.example.movieappcleanarchitecture.dagger.details.MovieDetailsSubComponent
import com.example.movieappcleanarchitecture.dagger.favorites.FavoriteModule
import com.example.movieappcleanarchitecture.dagger.favorites.FavoritesSubComponent
import com.example.movieappcleanarchitecture.dagger.network.NetworkModule
import com.example.movieappcleanarchitecture.dagger.popular.PopularMoviesModule
import com.example.movieappcleanarchitecture.dagger.popular.PopularSubComponent

class MovieApplication : Application()//, HasActivityInjector
 {
    lateinit var mainComponent: MainComponent

     private var popularMoviesComponent: PopularSubComponent? = null
     private var favoriteMoviesComponent: FavoritesSubComponent? = null
     private var movieDetailsComponent: MovieDetailsSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDependencies()

    }

    private fun initDependencies() {
        mainComponent = DaggerMainComponent.builder()
                .appModule(AppModule(applicationContext))
                .networkModule(NetworkModule(THE_MOVIE_URL, API_KEY_VALUE))
                .dataModule(DataModule())
                .build()



    }

    fun createPopularComponenet(): PopularSubComponent {
        popularMoviesComponent = mainComponent.plus(PopularMoviesModule())
        return popularMoviesComponent!!
    }


     fun releasePopularComponent() {
         popularMoviesComponent = null
     }
     fun createFavoritesComponent() : FavoritesSubComponent {
         favoriteMoviesComponent = mainComponent.plus(FavoriteModule())
         return favoriteMoviesComponent!!
     }
     fun releaseFavoritesComponent() {
         favoriteMoviesComponent = null
     }

     fun createDetailsComponent(): MovieDetailsSubComponent {
         movieDetailsComponent = mainComponent.plus(MovieDetailsModule())
         return movieDetailsComponent!!
     }
     fun releaseDetailsComponent() {
         movieDetailsComponent = null
     }

 }
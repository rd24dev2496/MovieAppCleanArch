package com.example.movieappcleanarchitecture.data.api

import com.example.movieappcleanarchitecture.data.entities.DetailsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getMovieDetails(@Path("id") movieId: Int): Observable<DetailsData>

    @GET("movie/popular") ///movie/now_playing
    fun getPopularMovies(): Observable<MovieListResult>

}
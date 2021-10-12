package com.example.movieappcleanarchitecture.presentation.ui.popmovies.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.movieappcleanarchitecture.R
import com.example.movieappcleanarchitecture.presentation.entities.Movie
import kotlinx.android.synthetic.main.popular_movies_item_row.view.*

class PopularMoviesAdapter constructor(private val onMovieSelected:
                                       (Movie, View) -> Unit) :
        RecyclerView.Adapter<PopularMoviesAdapter.MovieCellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movies_item_row,
                parent,
                false)
        return MovieCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onMovieSelected)
    }

    private val movies: MutableList<Movie> = mutableListOf()


    override fun getItemCount(): Int {
        return movies.size
    }


    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, listener: (Movie, View) -> Unit) = with(itemView) {


            title.text = movie.originalTitle

            movie.posterPath?.let {
                image.load(it)
            }
            setOnClickListener { listener(movie, itemView) }
        }

    }
}
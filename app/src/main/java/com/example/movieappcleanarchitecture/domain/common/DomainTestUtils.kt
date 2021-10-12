package com.example.movieappcleanarchitecture.domain.common

import com.example.movieappcleanarchitecture.domain.entities.MovieDetailsEntity
import com.example.movieappcleanarchitecture.domain.entities.MovieEntity
import com.example.movieappcleanarchitecture.domain.entities.ReviewEntity
import com.example.movieappcleanarchitecture.domain.entities.VideoEntity


class DomainTestUtils {

    companion object {
        fun getTestMovieEntity(id: Int): MovieEntity {
            return MovieEntity(
                    id = id,
                    title = "Movie$id",
                    originalTitle = "Movie$id",
                    backdropPath = "",
                    posterPath = "",
                    originalLanguage = "",
                    releaseDate = "")
        }

        fun getTestMovieEntityWithDetails(id: Int): MovieEntity {
            val movieEntity = getTestMovieEntity(id)
            movieEntity.details = MovieDetailsEntity(
                    overview = "Overview goes here $id",
                    videos = (0..2).map {
                        VideoEntity(
                                "video-$it",
                                "Video$it",
                                "key-$it")
                    },
                    homepage = "http://www.test.$id.org",
                    tagline = "I'm $id tag line!",
                    reviews = (0..4).map {
                        ReviewEntity(
                                "review-$it",
                                "Author$it",
                                "My score =$it")
                    }
            )
            return movieEntity
        }

        fun generateMovieEntityList(): List<MovieEntity> {
            return (0..4).map { getTestMovieEntity(it) }
        }
    }
}
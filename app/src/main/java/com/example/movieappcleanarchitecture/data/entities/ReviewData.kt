package com.example.movieappcleanarchitecture.data.entities
data class ReviewData(
        var id: String,
        var author: String,
        var content: String? = null
)
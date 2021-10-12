package com.example.movieappcleanarchitecture.presentation.entities

data class Video (
        var id: String,
        var name: String,
        var url: String? = null
)
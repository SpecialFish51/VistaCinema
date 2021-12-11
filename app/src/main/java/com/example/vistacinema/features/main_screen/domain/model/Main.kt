package com.example.vistacinema.features.main_screen.domain.model

data class Main(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
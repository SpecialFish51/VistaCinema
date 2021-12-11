package com.example.vistacinema.features.main_screen.domain

class MoviesInteractor(private val repo: MainScreenRepo) {
    suspend fun getMovies() = attempt { repo.getMovies().toDomain() }
}
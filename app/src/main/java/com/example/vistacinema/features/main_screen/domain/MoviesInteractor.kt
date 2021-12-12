package com.example.vistacinema.features.main_screen.domain

import com.example.vistacinema.features.base.func.attempt
import com.example.vistacinema.features.main_screen.data.api.MainScreenRepo
import com.example.vistacinema.features.main_screen.data.api.toDomain

class MoviesInteractor(private val repo: MainScreenRepo) {
    suspend fun getMovies() = attempt { repo.getMovies().toDomain() }
}
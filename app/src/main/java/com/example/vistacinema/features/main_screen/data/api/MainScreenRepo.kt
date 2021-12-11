package com.example.vistacinema.features.main_screen.data.api

interface MainScreenRepo {
    suspend fun getMovies(): MainModel
}
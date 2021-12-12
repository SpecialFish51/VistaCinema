package com.example.vistacinema.features.main_screen.data.api

import com.example.vistacinema.features.main_screen.data.api.model.MainModel

interface MainScreenRepo {
    suspend fun getMovies(): MainModel
}
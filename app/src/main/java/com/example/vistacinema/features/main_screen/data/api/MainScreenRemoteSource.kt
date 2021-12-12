package com.example.vistacinema.features.main_screen.data.api

import com.example.vistacinema.features.main_screen.data.api.model.MainModel

class MainScreenRemoteSource(private val api: MainScreenApi) {
    suspend fun getMoves(): MainModel {
        return api.getMoves()
    }
}
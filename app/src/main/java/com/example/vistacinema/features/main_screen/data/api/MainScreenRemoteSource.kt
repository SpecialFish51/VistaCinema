package com.example.vistacinema.features.main_screen.data.api

class MainScreenRemoteSource(private val api: MainScreenApi) {
    suspend fun getMoves(): MainModel {
        return api.getMoves()
    }
}
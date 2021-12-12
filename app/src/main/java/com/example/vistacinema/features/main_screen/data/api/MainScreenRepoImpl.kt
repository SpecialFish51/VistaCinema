package com.example.vistacinema.features.main_screen.data.api

import com.example.vistacinema.features.main_screen.data.api.model.MainModel

class MainScreenRepoImpl(private val remoteSource: MainScreenRemoteSource): MainScreenRepo {
    override suspend fun getMovies(): MainModel {
        return  remoteSource.getMoves()
    }
}
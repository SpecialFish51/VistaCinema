package com.example.vistacinema.features.main_screen.data.api

class MainScreenRepoImpl(private val remoteSource: MainScreenRemoteSource): MainScreenRepo {
    override suspend fun getMovies(): MainModel {
        return  remoteSource.getMoves()
    }
}
package com.example.vistacinema.features.main_screen.data.api

import retrofit2.http.GET

interface MainScreenApi {
    @GET(Constants.BASE_MOVIES_PATH)
    suspend fun getMoves():MainModel
}
package com.example.vistacinema.features.di

import com.example.vistacinema.features.base.constants.Constants
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_MOVIES_URL)
            .client(get())
            .build()
    }

    single<Cicerone<Router>> {
        Cicerone.create()
    }

    single<Router> {
        get<Cicerone<Router>>().router
    }

    single<NavigatorHolder> {
        get<Cicerone<Router>>().getNavigatorHolder()
    }
}
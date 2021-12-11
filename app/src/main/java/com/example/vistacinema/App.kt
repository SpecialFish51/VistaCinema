package com.example.vistacinema

import android.app.Application

import com.example.vistacinema.features.di.appModule
import com.example.vistacinema.features.main_screen.di.maiScreenModule
import com.example.vistacinema.features.movie_card.di.moduleMovieCard
import com.example.vistacinema.features.player.di.modulePlayer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, maiScreenModule, moduleMovieCard, modulePlayer)
        }

    }
}
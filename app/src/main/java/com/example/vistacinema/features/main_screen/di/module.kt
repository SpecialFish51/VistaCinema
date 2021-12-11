package com.example.vistacinema.features.main_screen.di

import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val maiScreenModule = module {

    single<MainScreenApi> {
        get<Retrofit>().create(MainScreenApi::class.java)
    }

    single<MainScreenRemoteSource> {
        MainScreenRemoteSource(get<MainScreenApi>())
    }

    single<MainScreenRepo> {
        MainScreenRepoImpl(get<MainScreenRemoteSource>())
    }

    single<MoviesInteractor> {
        MoviesInteractor(get<MainScreenRepo>())
    }

    viewModel {
        MoviesListViewModel(get<MoviesInteractor>(), get<Router>())
    }
}
package com.example.vistacinema.features.movie_card.di

import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMovieCard = module {

    viewModel {
        MovieCardViewModel(get<Router>())
    }
}
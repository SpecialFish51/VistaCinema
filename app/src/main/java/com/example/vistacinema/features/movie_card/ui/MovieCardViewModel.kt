package com.example.vistacinema.features.movie_card.ui

import com.example.vistacinema.features.base.navigation.Screens
import com.example.vistacinema.features.base.view_model.BaseViewModel
import com.example.vistacinema.features.base.view_model.Event
import com.github.terrakok.cicerone.Router

class MovieCardViewModel(private val router: Router) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(null, emptyList())

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnPlayClick -> {
                router.navigateTo(Screens.player(event.movie.video, event.movie.title))
            }
            is UiEvent.OnBackClicked -> {
                router.exit()
            }
            is UiEvent.OnMovieCardClick -> {
                //TODO
            }
        }
        return null
    }
}
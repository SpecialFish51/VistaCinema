package com.example.vistacinema.features.player.ui

import com.example.vistacinema.features.base.view_model.BaseViewModel
import com.example.vistacinema.features.base.view_model.Event
import com.github.terrakok.cicerone.Router

class PlayerFragmentViewModel(private val router: Router) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState("")

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnBackPressed -> {
                router.exit()
            }
        }
        return null
    }
}
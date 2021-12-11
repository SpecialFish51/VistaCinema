package com.example.vistacinema.features.player.ui

import com.example.vistacinema.features.base.view_model.Event

data class ViewState(
    val url: String
)

sealed class UiEvent : Event {
    object OnBackPressed : UiEvent()
}
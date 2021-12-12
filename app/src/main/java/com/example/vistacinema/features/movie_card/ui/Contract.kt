package com.example.vistacinema.features.movie_card.ui

import com.example.vistacinema.features.base.view_model.Event
import com.example.vistacinema.features.main_screen.domain.model.Movie

data class ViewState(
    val movie: Movie?,
    val similarMovies: List<Movie>
)

sealed class UiEvent : Event {
    data class OnPlayClick(val movie: Movie) : UiEvent()
    data class OnMovieCardClick(val movie: Movie) : UiEvent()
    object OnBackClicked : UiEvent()
}

sealed class SingleEvent : Event {
    data class OpenMovieCard(val movie: Movie) : SingleEvent()
}
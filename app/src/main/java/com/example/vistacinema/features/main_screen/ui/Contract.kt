package com.example.vistacinema.features.main_screen.ui

import com.example.vistacinema.features.base.view_model.Event

data class ViewState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?,
    val column: Int,
    val sorted: Sorting?
)

sealed class UiEvent : Event {
    data class OnCardMovieClick(val movie: Movie) : UiEvent()
    object OnChangeGridClick : UiEvent()
    object OnSortClick : UiEvent()
    object GetMovies : UiEvent()
    object OnSortDateClick : UiEvent()
    object OnSortNameClick : UiEvent()
    object OnSortPopularityClick : UiEvent()
    object OnSortRatingClick : UiEvent()
}

sealed class DataEvent : Event {
    object GetMovies : DataEvent()
    data class SuccessMoviesRequest(val movies: List<Movie>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String?) : DataEvent()
    data class LoadData(val isLoading: Boolean) : DataEvent()
}

sealed class SingleEvent : Event {
    object ShowPopupMenu : SingleEvent()
}
package com.example.vistacinema.features.main_screen.ui

import com.github.terrakok.cicerone.Router

class MoviesListViewModel(private val interactor: MoviesInteractor, private val router: Router) :
    BaseViewModel<ViewState>() {

    companion object {
        const val COLUMN_MAX = 3
        const val COLUMN_MIN = 1
    }

    init {
        viewModelScope.launch {
            while (true) {
                processDataEvent(DataEvent.GetMovies)
                delay(Constants.GET_MOVIES_DELAY)
            }
        }
    }

    val singleLiveEvent = SingleLiveEvent<SingleEvent>()

    override fun initialViewState(): ViewState {
        return ViewState(
            movies = emptyList(),
            isLoading = true,
            errorMessage = null,
            column = COLUMN_MIN,
            sorted = null
        )
    }


    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.GetMovies -> {
                processDataEvent(DataEvent.LoadData(true))
                interactor.getMovies().fold(
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessMoviesRequest(it.results))
                    },
                    onError = {
                        processDataEvent(DataEvent.ErrorMoviesRequest(it.localizedMessage ?: ""))
                    }
                )
            }
            is DataEvent.GetMovies -> {
                interactor.getMovies().fold(
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessMoviesRequest(it.results))
                    },
                    onError = {
                        processDataEvent(DataEvent.ErrorMoviesRequest(it.localizedMessage ?: ""))
                    }
                )
            }
            is UiEvent.OnCardMovieClick -> {
                router.navigateTo(Screens.movieCard(event.movie, previousState.movies))
            }
            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(
                    movies = event.movies.sortedMovies(previousState.sorted),
                    isLoading = false,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
            is DataEvent.LoadData -> {
                return previousState.copy(
                    isLoading = event.isLoading
                )
            }
            is UiEvent.OnChangeGridClick -> {
                return if (previousState.column < COLUMN_MAX) {
                    previousState.copy(column = previousState.column + 1)
                } else {
                    previousState.copy(column = COLUMN_MIN)
                }
            }
            is UiEvent.OnSortClick -> {
                singleLiveEvent.value = SingleEvent.ShowPopupMenu
            }
            is UiEvent.OnSortDateClick -> {
                return previousState.copy(
                    sorted = SortedBy.Date,
                    movies = previousState.movies.sortedMovies(SortedBy.Date)
                )
            }
            is UiEvent.OnSortNameClick -> {
                return previousState.copy(
                    sorted = SortedBy.Name,
                    movies = previousState.movies.sortedMovies(SortedBy.Name)
                )
            }
            is UiEvent.OnSortPopularityClick -> {
                return previousState.copy(
                    sorted = SortedBy.Popularity,
                    movies = previousState.movies.sortedMovies(SortedBy.Popularity)
                )
            }
            is UiEvent.OnSortRatingClick -> {
                return previousState.copy(
                    sorted = SortedBy.Rating,
                    movies = previousState.movies.sortedMovies(SortedBy.Rating)
                )
            }
        }
        return null
    }

    private fun List<Movie>.sortedMovies(sorting: Sorting?): List<Movie> {
        return when (sorting) {
            is SortedBy.Date -> {
                this.sortedByDescending { it.releaseDate }
            }
            is SortedBy.Name -> {
                this.sortedBy { it.title }
            }
            is SortedBy.Popularity -> {
                this.sortedByDescending { it.popularity }
            }
            is SortedBy.Rating -> {
                this.sortedByDescending { it.voteAverage }
            }
            else -> this
        }
    }
}
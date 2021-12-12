package com.example.vistacinema.features.base.navigation

import com.example.vistacinema.features.main_screen.domain.model.Movie
import com.example.vistacinema.features.main_screen.ui.MoviesListFragment
import com.example.vistacinema.features.movie_card.ui.MovieCardFragment
import com.example.vistacinema.features.player.ui.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun mainScreen() = FragmentScreen { MoviesListFragment.newInstance() }

    fun movieCard(movie: Movie, similarMovies: List<Movie>) =
        FragmentScreen {
            MovieCardFragment.newInstance(
                movie = movie,
                similarMovies = similarMovies
            )
        }

    fun player(url: String, name: String) =
        FragmentScreen { PlayerFragment.newInstance(movieUrl = url, movieName = name) }
}
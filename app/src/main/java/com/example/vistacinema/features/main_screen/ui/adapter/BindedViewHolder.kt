package com.example.vistacinema.features.main_screen.ui.adapter

import com.example.vistacinema.features.main_screen.domain.model.Movie

interface BindedViewHolder {
    fun bind(item: Movie, onMovieClick: (Movie) -> Unit)
}
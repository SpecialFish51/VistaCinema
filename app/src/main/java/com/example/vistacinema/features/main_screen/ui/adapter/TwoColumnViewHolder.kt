package com.example.vistacinema.features.main_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TwoColumnViewHolder(private val binding: ItemMovieTwoColumnBinding) :
    RecyclerView.ViewHolder(binding.root), BindedViewHolder {
    constructor(parent: ViewGroup) : this(
        ItemMovieTwoColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun bind(item: Movie, onMovieClick: (Movie) -> Unit) {

        binding.apply {
            cardView.setThrottledClickListener { onMovieClick(item) }
            tvYear.text = item.releaseDate?.toStringFormat("yyyy") ?: ""
            tvVoteAverage.text = item.voteAverage.toString()
            ivMovieImage.loadImage(item.posterPath)
        }
    }
}
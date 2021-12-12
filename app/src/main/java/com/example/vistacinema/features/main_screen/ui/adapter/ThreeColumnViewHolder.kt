package com.example.vistacinema.features.main_screen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vistacinema.databinding.ItemMovieThreeColumnBinding
import com.example.vistacinema.features.base.utils.loadImage
import com.example.vistacinema.features.base.utils.setThrottledClickListener
import com.example.vistacinema.features.base.utils.toStringFormat
import com.example.vistacinema.features.main_screen.domain.model.Movie

class ThreeColumnViewHolder(private val binding: ItemMovieThreeColumnBinding) :
    RecyclerView.ViewHolder(binding.root), BindedViewHolder {
    constructor(parent: ViewGroup) : this(
        ItemMovieThreeColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
package com.example.vistacinema.features.main_screen.ui.adapter

import com.example.vistacinema.databinding.ItemMovieThreeColumnBinding
import com.example.vistacinema.features.base.utils.loadImage
import com.example.vistacinema.features.base.utils.setThrottledClickListener
import com.example.vistacinema.features.base.utils.toStringFormat
import com.example.vistacinema.features.main_screen.domain.model.Movie
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun moviesAdapterDelegate(itemClickedListener: (Movie) -> Unit) =
    adapterDelegateViewBinding<Movie, Movie, ItemMovieThreeColumnBinding>(
        { layoutInflater, root -> ItemMovieThreeColumnBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.cardView.setThrottledClickListener {
            itemClickedListener(item)
        }
        bind {
            binding.apply {
                tvYear.text = item.releaseDate?.toStringFormat("yyyy") ?: ""
                tvVoteAverage.text = item.voteAverage.toString()
                ivMovieImage.loadImage(item.posterPath)
            }
        }
    }

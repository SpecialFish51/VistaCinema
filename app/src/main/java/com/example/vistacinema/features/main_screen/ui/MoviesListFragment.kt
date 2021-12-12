package com.example.vistacinema.features.main_screen.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vistacinema.R
import com.example.vistacinema.databinding.FragmentMoviesListBinding
import com.example.vistacinema.features.base.utils.setThrottledClickListener
import com.example.vistacinema.features.main_screen.ui.adapter.SimpleAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private val viewModel by viewModel<MoviesListViewModel>()
    private val binding by viewBinding(FragmentMoviesListBinding::bind)
    private val simpleAdapter by lazy {
        SimpleAdapter(emptyList(), manager) {
            viewModel.processUiEvent(UiEvent.OnCardMovieClick(it))
        }
    }

    private val manager by lazy { GridLayoutManager(requireContext(), 1) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.srlMovies.apply {
            setOnRefreshListener {
                viewModel.processUiEvent(UiEvent.GetMovies)
            }
        }
        binding.rvMovies.apply {
            layoutManager = manager
            adapter = simpleAdapter
        }
        binding.ivMosaic.setThrottledClickListener {
            viewModel.processUiEvent(UiEvent.OnChangeGridClick)
        }
        binding.ivSort.setThrottledClickListener {
            viewModel.processUiEvent(UiEvent.OnSortClick)
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        viewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    private fun render(viewState: ViewState) {
        manager.spanCount = viewState.column
        simpleAdapter.updateArticles(viewState.movies)
        binding.srlMovies.isRefreshing = viewState.isLoading
        if (viewState.errorMessage != null) {
            Snackbar.make(
                binding.mainContainer,
                getString(R.string.loading_error),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.ShowPopupMenu -> {
                PopupMenu(requireContext(), binding.ivSort).apply {
                    inflate(R.menu.menu_sort_main_screen)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.sortDate -> {
                                viewModel.processUiEvent(UiEvent.OnSortDateClick)
                            }
                            R.id.sortName -> {
                                viewModel.processUiEvent(UiEvent.OnSortNameClick)
                            }
                            R.id.sortPopularity -> {
                                viewModel.processUiEvent(UiEvent.OnSortPopularityClick)
                            }
                            R.id.sortRating -> {
                                viewModel.processUiEvent(UiEvent.OnSortRatingClick)
                            }
                        }
                        true
                    }
                }.show()
            }
        }
    }
}
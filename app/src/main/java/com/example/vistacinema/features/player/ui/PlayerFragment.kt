package com.example.vistacinema.features.player.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vistacinema.R
import com.example.vistacinema.databinding.FragmentPlayerBinding
import com.example.vistacinema.features.base.utils.hideSystemUI
import com.example.vistacinema.features.base.utils.showSystemUI
import com.example.vistacinema.features.player.service.PlayerService
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.vistacinema.features.base.utils.onBackPressed
import com.google.android.exoplayer2.audio.AudioAttributes

class PlayerFragment : Fragment(R.layout.fragment_player) {
    companion object {
        const val KEY_MOVIE_URL = "movieUrl"
        const val MOVIE_NAME = "movieName"
        fun newInstance(movieUrl: String, movieName: String) = PlayerFragment().apply {
            arguments = bundleOf(Pair(KEY_MOVIE_URL, movieUrl), Pair(MOVIE_NAME, movieName))
        }
    }

    private val viewModel by viewModel<PlayerFragmentViewModel>()
    private val binding by viewBinding(FragmentPlayerBinding::bind)
    private val movieUrl: String by lazy { requireArguments().getString(KEY_MOVIE_URL)!! }
    private val movieName: String by lazy { requireArguments().getString(MOVIE_NAME)!! }
    private lateinit var intent: Intent

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            when (service) {
                is PlayerService.PlayerServiceBinder -> {
                    binding.playerView.player = service.getPlayer()
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intent = Intent(requireContext(), PlayerService::class.java).apply {
            putExtra(PlayerService.KEY_MOVIE_URL, movieUrl)
            putExtra(PlayerService.MOVIE_NAME, movieName)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireContext().startForegroundService(intent)
        } else {
            requireContext().startService(intent)
        }
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        requireActivity().onBackPressed(viewLifecycleOwner) {
            requireActivity().stopService(intent)
            viewModel.processUiEvent(UiEvent.OnBackPressed)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unbindService(serviceConnection)
    }

    override fun onStart() {
        super.onStart()
        hideSystemUI(requireActivity().window, binding.playerView)
    }

    override fun onStop() {
        super.onStop()
        showSystemUI(requireActivity().window, binding.playerView)
    }
}
package com.example.vistacinema.features.player.di

import androidx.leanback.media.PlayerAdapter
import com.example.vistacinema.features.player.service.PlayerService
import com.example.vistacinema.features.player.ui.PlayerFragmentViewModel
import com.github.terrakok.cicerone.Router
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val modulePlayer = module {

    factory<ExoPlayer> {
        ExoPlayer.Builder(androidContext()).build()
    }

    single<PlayerAdapter> {
        PlayerAdapter(androidContext())
    }

    single<PlayerNotificationManager.Builder> {
        PlayerNotificationManager.Builder(
            androidContext(),
            PlayerService.NOTIFICATION_ID,
            PlayerService.CHANNEL_ID
        ).apply {
            setMediaDescriptionAdapter(get<PlayerAdapter>())
        }
    }

    viewModel<PlayerFragmentViewModel> {
        PlayerFragmentViewModel(get<Router>())
    }
}
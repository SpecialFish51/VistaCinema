package com.example.vistacinema.features.player.service.notifications

import android.widget.Toast
import com.example.vistacinema.R
import com.example.vistacinema.features.player.service.PlayerService
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player

class PlayerListener(private val playerService: PlayerService) : Player.Listener {
    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(
            playerService,
            playerService.getString(R.string.loading_error),
            Toast.LENGTH_LONG
        ).show()
    }
}
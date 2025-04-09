package org.mathieu.cleanrmapi.ui.core.managers

import android.content.Context
import android.media.MediaPlayer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.R

/**
 * Android implementation of SoundManager.
 * Uses MediaPlayer to play a sound effect when clicked.
 */

actual object SoundManager : KoinComponent {
    private var mediaPlayer: MediaPlayer? = null
    private val context: Context by inject()

    /**
     * Plays the sound defined in res/raw/thud_sound.mp3.
     */
    actual fun playClickSound() {
        mediaPlayer = MediaPlayer.create(context, R.raw.thud_sound)
        mediaPlayer?.start()
    }
}
package org.mathieu.cleanrmapi.ui.core.managers

import java.io.BufferedInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent

/**
 * Desktop implementation of SoundManager.
 * Plays an audio file (.wav) located in the resources folder.
 */
actual object SoundManager {
    actual fun playClickSound() {
        try {
            val resourceStream = SoundManager::class.java.getResourceAsStream("/sounds/thud_sound.wav")
            resourceStream?.let { stream ->
                val bufferedStream = BufferedInputStream(stream)
                val audioInputStream = AudioSystem.getAudioInputStream(bufferedStream)
                val clip = AudioSystem.getClip()
                clip.open(audioInputStream)
                clip.addLineListener { event ->
                    if (event.type == LineEvent.Type.STOP) {
                        clip.close()
                    }
                }
                clip.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
package org.mathieu.cleanrmapi.ui.core.managers

expect object SoundManager {
    /**
     * Common interface for playing sound effects.
     * Each platform will have to provide its own implementation via an actual file.
     */
    fun playClickSound()
}
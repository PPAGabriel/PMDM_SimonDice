package com.example.simondice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Class that contains the data of the game
 * @property round is the actual round of the game
 * @property sequence is the sequence of colors that the user has to follow
 * @property userSequence is the sequence of colors that the user introduces
 * @property record is the record of the game
 * @property status is the status of the game
 */

object Data {
    var round: Int = 0
    var sequence = mutableListOf<Int>()
    var userSequence = mutableListOf<Int>()
    var numColors= listOf(Colors.BLUE.color, Colors.GREEN.color, Colors.RED.color, Colors.YELLOW.color)
    var record: Int = 0
    var state: State = State.START
    
}

/**
 * Enum class with the status of the game
 */

enum class State {
    START,
    SEQUENCE,
    WAITING,
    INPUT,
    CHECKING,
    PLAYING,
    WIN,
    FINISH
}

/**
 * Enum class with the colors of the game
 */

enum class Colors(var color: MutableState<Color>) {
    RED(mutableStateOf(Color.Red)),
    BLUE(mutableStateOf(Color.Blue)),
    GREEN(mutableStateOf(Color.Green)),
    YELLOW(mutableStateOf(Color.Yellow))
}
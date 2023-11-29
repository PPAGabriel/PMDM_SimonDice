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
    var round = mutableStateOf(0)
    var playStatus= mutableStateOf("START")
    var sequence = mutableListOf<Int>()
    var userSequence = mutableListOf<Int>()
    var colors= listOf(
        Colors.RED.color,
        Colors.BLUE.color,
        Colors.YELLOW.color,
        Colors.GREEN.color)
    var record= mutableStateOf(0)
    var state: State = State.START
    var numColors=Colors.values()
    var colorPath: Color= Color.White
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
    FINISH
}

/**
 * Enum class with the colors of the game
 */

enum class Colors(var color: MutableState<Color>) {
    RED(mutableStateOf(Color.Red)),
    BLUE(mutableStateOf(Color.Blue)),
    YELLOW(mutableStateOf(Color.Yellow)),
    GREEN(mutableStateOf(Color.Green))
}
package com.example.simondice

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

enum class Colors {
    RED,
    BLUE,
    GREEN,
    YELLOW
}
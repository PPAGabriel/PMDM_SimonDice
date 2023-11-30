package com.example.simondice.ui.theme

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondice.Data
import com.example.simondice.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

/**
 * ViewModel of the game
 */
class MyViewModel():ViewModel() {
    private val TAG_LOG: String="MVM"

    init{
        Log.d(TAG_LOG,"Inicializamos ViewModel")
    }

    /**
     * Generate a random number between 0 and -1
     * @param max is the maximum number that can be generated
     * @return a random number
     */

    fun randomNumber(max: Int): Int {
        return (0..max - 1).random()
    }

    /**
     * Restart the game
     */
    fun restartGame() {
        restartRound()
        restartSequence()
        restartUserSequence()
        Data.state = State.START
    }

    /**
     * Restart the round
     */
    fun restartRound() {
        Data.round.value = 0
    }

    /**
     * Restart the sequence
     */
    fun restartSequence() {
        Data.sequence.clear()
    }

    /**
     * Restart the user sequence
     */
    fun restartUserSequence() {
        Data.userSequence.clear()
    }

    /**
     * Increment the sequence and show it
     */
    fun incrementSequence(time: Long) {
        Data.state = State.SEQUENCE
        Log.d(TAG_LOG,Data.state.toString())
        Data.sequence.add(randomNumber(4))
        showSequenceRun(time)
    }

    /**
     * Show the sequence
     */

    fun showSequenceRun(time:Long) = runBlocking {
        showSequence(time)
    }
    fun showSequence(time: Long) {
        Log.d(TAG_LOG, "Mostramos la secuencia")
        viewModelScope.launch {
            for (i in Data.sequence) {
                Log.d(TAG_LOG, "Mostramos el color $i")
                Data.colorPath= Data.colors[i].value
                Data.numColors[i].color.value= darkestColor(Data.colorPath,0.5f)

                delay(time)
                Data.numColors[i].color.value= Data.colorPath
                delay(time)
            }
            Data.state = State.WAITING
            Log.d(TAG_LOG, Data.state.toString())
        }
        Log.d(TAG_LOG, Data.state.toString())
    }

    /**
     * Darkest the color of the button
     */
    fun darkestColor(color: Color,factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }

    /**
     * Increment the user sequence
     */
    fun incrementUserSequenceRun(numColor: Int) = runBlocking {
        incrementUserSequence(numColor)
    }
    fun incrementUserSequence(color: Int) {
        Data.state = State.INPUT
        Log.d(TAG_LOG,Data.state.toString())
        Data.userSequence.add(color)

        viewModelScope.launch {
            Data.colorPath=Data.numColors[color].color.value
            Data.numColors[color].color.value= Color.White
            delay(150L)
            Data.numColors[color].color.value= Data.colorPath
        }

        Data.state=State.WAITING
        Log.d(TAG_LOG, Data.state.toString())
    }


    /**
     * Check if the user sequence is correct
     */
    fun checkUserSequence() {
        Data.state= State.CHECKING
        Log.d(TAG_LOG, Data.state.toString())
        if (Data.userSequence == Data.sequence) {
            Data.round.value++

            if (Data.round.value > Data.record.value) {
                Data.record.value = Data.round.value
            }

            Data.userSequence.clear()
            if(Data.round.value>9){
                incrementSequence(100L)
            }else if(Data.round.value>7 && Data.round.value<=9){
                incrementSequence(150L)
            } else if(Data.round.value>5 && Data.round.value<=7){
                incrementSequence(200L)
            }else if(Data.round.value>3 && Data.round.value<=5){
                incrementSequence(300L)
            }else{
                incrementSequence(500L)
            }
        } else {
            Log.d(TAG_LOG, "La secuencia es incorrecta")
            Data.state = State.FINISH

            Toast.makeText(ctxt,"G A M E   O V E R",Toast.LENGTH_SHORT).show()

            Data.playStatus.value="START"
            restartGame()
            Log.d(TAG_LOG, Data.state.toString())
        }
    }

    /**
     * Get the round
     * @return the round
     */
    fun getRound(): Int {
        return Data.round.value
    }

    /**
     * Get the record
     */
    fun getRecord(): Int {
        return Data.record.value
    }

    /**
     * Get the status of the game
     */
    fun getStatus(): String {
        return Data.playStatus.value
    }

    /**
     * Change the status of the game
     */
    fun changeStatus() {
        if (Data.playStatus.value == "START") {
            Data.playStatus.value= "RESTART"
            //Execute the first round
            Data.round.value++
            incrementSequence(500L)
        } else {
            Data.playStatus.value= "START"
            restartGame()
        }
    }

}
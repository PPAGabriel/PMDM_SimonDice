package com.example.simondice.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.simondice.Colors
import com.example.simondice.Data
import com.example.simondice.R
import com.example.simondice.State
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * ViewModel of the game
 */

class MyViewModel():ViewModel() {
    private val TAG_LOG: String="Mensaje de ViewModel"

    var _round= mutableStateOf(0)
    var _statusC= mutableStateOf("START")

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
        Data.round = 0
    }

    /**
     * Restart the sequence
     */
    fun restartSequence() {
        Data.sequence = mutableListOf()
        Colors.GREEN.color.value = Color.Green
        Colors.RED.color.value = Color.Red
        Colors.BLUE.color.value = Color.Blue
        Colors.YELLOW.color.value = Color.Yellow
    }

    /**
     * Restart the user sequence
     */
    fun restartUserSequence() {
        Data.userSequence = mutableListOf()
    }

    /**
     * Increase the sequence of colors
     */
    fun increaseSequence() = runBlocking{
        Data.state = State.SEQUENCE
        addColorToSequence()
        showSequence()
        Data.state= State.WAITING
    }

    /**
     * Add a color to the sequence
     */
    fun addColorToSequence() {
        Data.sequence.add(randomNumber(4))
    }

    /**
     * Show the sequence of colors to the user
     */

    suspend fun showSequence()= coroutineScope {
        //TODO: Show the sequence of colors to the user
        Log.d("Secuencia","${Data.sequence}")
       launch {
           for (i in Data.sequence) {
               if (Data.numColors[i].equals(Colors.BLUE.color)) {
                   Colors.BLUE.color.value = Color(255, 255, 255)
               }
               if (Data.numColors[i].equals(Colors.RED.color)) {
                   Colors.RED.color.value = Color(255, 255, 255)
               }
               if (Data.numColors[i].equals(Colors.YELLOW.color)) {
                   Colors.YELLOW.color.value = Color(255, 255, 255)
               }
               if (Data.numColors[i].equals(Colors.GREEN.color)) {
                   Colors.GREEN.color.value = Color(255, 255, 255)
               }

               delay(1000L)
               
           }
       }

    }

    /**
     * Increase the sequence of the user
     * @param color Color introduced by the user
     */
    fun increaseUserSequence(color: Int) {
        Data.state= State.INPUT
        Data.userSequence.add(color)
    }

    /**
     * Check if the user sequence is correct
     * @return true if the user sequence is correct, false if not
     */

    fun checkUserSequence(): Boolean {
        Data.state= State.CHECKING
        var correct: Boolean = true
        for (i in 0..Data.userSequence.size - 1) {
            if (Data.userSequence[i] != Data.sequence[i]) {
                correct = false
                break
            }
        }
        return correct
    }


    //////////////////////////////////////////

    fun changeStatus(){
        if (_statusC.value.equals("START")){
            _statusC.value = "RESET"

            increaseSequence()


        } else {
            _statusC.value = "START"

            restartGame()

        }
    }


    fun incrementN(){
        _round.value++
    }

    fun getRound():Int{
        return _round.value
    }


    fun getStatus():String{
        return _statusC.value
    }

    @Composable
    fun UInterface(myViewModel: MyViewModel, modifier: Modifier = Modifier) {

        var myOwnColor= Color(220,122,255)
        Column {
            Column {
                round(myViewModel,modifier = modifier)
            }

            colorButtons()

            Row {
                startButton(myViewModel, myColor = myOwnColor, modifier = modifier)
                roundButton(myViewModel, myColor = myOwnColor, modifier = modifier)
            }
        }
    }

    // APP's Preview
    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        SimonDiceTheme {
            UInterface(myViewModel=MyViewModel() )
        }
    }

    @Composable()
    fun round(myViewModel: MyViewModel,modifier: Modifier) {
        Text(
            text = "Ronda:",
            modifier = modifier
                .padding(250.dp,0.dp,0.dp,0.dp),
            textAlign = TextAlign.Right,
            fontSize = 40.sp,
            color = Color.White
        )

        if (myViewModel.getRound() > 10) {
            Text(
                text = "${myViewModel.getRound()}",
                fontSize = 60.sp,
                modifier = Modifier.padding(300.dp,0.dp,0.dp,0.dp),
                color = Color.White
            )
        } else {
            Text(
                text = "${myViewModel.getRound()}",
                fontSize = 40.sp,
                modifier = Modifier.padding(300.dp,0.dp,0.dp,0.dp),
                color = Color.White
            )
        }
    }
    @Composable
    fun startButton(myViewModel: MyViewModel,myColor: Color,modifier: Modifier){
        Button(
            onClick = {
                myViewModel.changeStatus()
            },
            modifier= modifier
                .height(90.dp)
                .width(160.dp)
                .padding(35.dp,0.dp,0.dp,0.dp),
            colors= ButtonDefaults.buttonColors(myColor)
        ) {
            Text(
                text = myViewModel.getStatus(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
    @Composable
    fun roundButton(myViewModel: MyViewModel,myColor: Color,modifier: Modifier){
        Button(
            onClick = {
                myViewModel.incrementN()
                Log.d("Funciones","Click!!!!!")
            },
            modifier= Modifier
                .height(90.dp)
                .width(160.dp)
                .padding(70.dp, 0.dp, 0.dp, 0.dp),
            colors= ButtonDefaults.buttonColors(myColor)
        ) {
            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "imagen",
                modifier = modifier
            )
        }
    }

    @Composable
    fun colorButtons(){
        Row (
            modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
            configColorButton(color = Color.Cyan)
            configColorButton(color = Color.Green)
        }
        Row (){
            configColorButton(color = Color.Red)
            configColorButton(color = Color.Yellow)
        }
    }

    @Composable
    fun configColorButton(color: Color){
        Column {
            Button(
                shape = RectangleShape,
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(50.dp, 50.dp)
                ,
                colors = ButtonDefaults.buttonColors(color)
            ){

            }
        }
    }
}
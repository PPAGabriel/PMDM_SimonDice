@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.R

open class UI {
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

//APP's Functions
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
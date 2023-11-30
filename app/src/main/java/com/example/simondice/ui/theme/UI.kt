@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.simondice.ui.theme

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.Colors
import com.example.simondice.Data
import com.example.simondice.R
import com.example.simondice.State
open class UI {
}

var ctxt: Context? = null

@Composable
fun UInterface(mVM: MyViewModel, modifier: Modifier = Modifier) {

    var myOwnColor= Color(156, 39, 176, 255)

    ctxt= LocalContext.current

    Column {
        Column {
            gameInfo(mVM,modifier = modifier)
        }

            colorButtons(mVM)


        Row {
            startButton(mVM, myColor = myOwnColor, modifier = modifier)
            sendButton(mVM, myColor = myOwnColor, modifier = modifier)
        }
    }
}

// APP's Preview
@Preview(showBackground = true)
@Composable
fun Preview() {
    SimonDiceTheme {
        UInterface(mVM=MyViewModel() )
    }
}

//APP's Functions

@Composable()
fun gameInfo(myViewModel: MyViewModel,modifier: Modifier) {

    Row(modifier = modifier
        .background(Color(156, 39, 176, 255))
        .fillMaxWidth()) {

            Column {
                Image(
                    painter = painterResource(R.drawable.medal),
                    contentDescription = "record",
                    modifier = modifier
                        .padding(25.dp, 10.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "${myViewModel.getRecord()}",
                    fontSize = 40.sp,
                    modifier = Modifier.padding(51.dp,0.dp,0.dp,10.dp),
                    color = Color.Yellow
                )
            }

        Column {
            Text(
                text = "Ronda: ${myViewModel.getRound()}",
                modifier = modifier
                    .padding(70.dp,40.dp,10.dp,0.dp),
                fontSize = 40.sp,
                color = Color.White
            )
        }
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
            .padding(40.dp, 0.dp, 0.dp, 0.dp),
        colors= ButtonDefaults.buttonColors(myColor)
    ) {
        Text(
            text = myViewModel.getStatus(),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}
@Composable
fun sendButton(myViewModel: MyViewModel,myColor: Color,modifier: Modifier){
    Button(
        onClick = {
            if (myViewModel.getStatus()=="START"){
                //Nothing changes
            }else{
                myViewModel.checkUserSequence()
            }
        },
        modifier= Modifier
            .height(90.dp)
            .width(190.dp)
            .padding(85.dp, 0.dp, 0.dp, 0.dp),
        colors= ButtonDefaults.buttonColors(myColor)
    ) {
        Image(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "play",
            modifier = modifier
        )
    }
}

@Composable
fun colorButtons(myViewModel: MyViewModel){
    Row (
        modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)){
        designColorButton(color = Colors.RED.color,myViewModel)
        designColorButton(color = Colors.BLUE.color,myViewModel)
    }
    Row (){
        designColorButton(color = Colors.YELLOW.color,myViewModel)
        designColorButton(color = Colors.GREEN.color,myViewModel)
    }
}

@Composable
fun designColorButton(color: MutableState<Color>, mVM: MyViewModel){
    Column {
        Button(
            shape = RectangleShape,
            onClick = {
                if (Data.state !=State.SEQUENCE && Data.playStatus.value=="RESTART"){
                    mVM.incrementUserSequenceRun(Data.colors.indexOf(color))
                }
            },
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(50.dp, 50.dp)
                .border(2.dp, Color.Black)
            ,
            colors = ButtonDefaults.buttonColors(color.value)
        ){

        }
    }
}
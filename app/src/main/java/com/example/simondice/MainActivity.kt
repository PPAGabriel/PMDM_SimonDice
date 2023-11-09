package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.simondice.ui.theme.UInterface
import com.example.simondice.ui.theme.MyViewModel
import com.example.simondice.ui.theme.SimonDiceTheme
/**
*Videogame of Simon Dice
**/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val miViewModel: MyViewModel = MyViewModel()
        setContent {
            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color(100,0,20)) {
                    UInterface(miViewModel)
                }
            }
        }
    }
}

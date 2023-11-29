package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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

    // APP's Variables
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mVM: MyViewModel = MyViewModel()
        setContent {
            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color(100,0,20)) {

                    UInterface(mVM)

                    // Music
                    mediaPlayer = MediaPlayer.create(this, R.raw.music)
                    mediaPlayer?.start()
                    mediaPlayer?.isLooping = true
                }
            }
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

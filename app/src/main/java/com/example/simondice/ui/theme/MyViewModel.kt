package com.example.simondice.ui.theme

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simondice.Data
import com.example.simondice.State

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


    fun incrementN(){
        _round.value++
    }

    fun getRound():Int{
        return _round.value
    }

    fun changeStatus(){
        if (_statusC.value.equals("START")){
            _statusC.value = "RESET"
        } else {
            _statusC.value = "START"

        }
    }

    fun getStatus():String{
        return _statusC.value
    }
}
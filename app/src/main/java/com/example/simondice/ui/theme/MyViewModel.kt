package com.example.simondice.ui.theme

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel():ViewModel() {
    private val TAG_LOG: String="Mensaje de ViewModel"

    var _numbers= mutableStateOf(0)
    var _numbers2= mutableStateOf(0)
    var _nameC= mutableStateOf("")
    var _nameC2= mutableStateOf("START")
    var _numberList= mutableStateListOf<Int>()

    init{
        Log.d(TAG_LOG,"Inicializamos ViewModel")
    }


    fun aumentoN(){
        _numbers2.value++
    }

    fun getNumero2():Int{
        return _numbers2.value
    }

    fun cambiarEstado(){
        if (_nameC2.value.equals("START")){
            _nameC2.value = "RESET"
        } else {
            _nameC2.value = "START"
        }
    }

    fun getNombre2():String{
        return _nameC2.value
    }
}
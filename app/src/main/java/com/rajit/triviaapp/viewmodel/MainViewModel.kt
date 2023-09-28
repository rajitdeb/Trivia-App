package com.rajit.triviaapp.viewmodel

import androidx.lifecycle.ViewModel
import com.rajit.triviaapp.util.Utils

class MainViewModel: ViewModel() {

    private var playerName: String = ""

    fun getPlayerName(): String {
        return playerName
    }

    fun setPlayerName(name: String): Boolean {
        val isValid = Utils.validatePlayerNameEdt(name)
        if(isValid) {
            playerName = Utils.convertToNormalCase(name)
            return true
        } else {
            return false
        }
    }

}
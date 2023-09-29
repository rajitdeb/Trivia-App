package com.rajit.triviaapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rajit.triviaapp.data.network.RetrofitInstance
import com.rajit.triviaapp.data.network.api.ApiService
import com.rajit.triviaapp.data.network.model.AllCategory
import com.rajit.triviaapp.data.repository.Repository
import com.rajit.triviaapp.util.Utils
import io.reactivex.rxjava3.core.Observable

class MainViewModel: ViewModel() {

    private val triviaApi: ApiService = RetrofitInstance().apiService
    private val repository: Repository = Repository(triviaApi)

    /** PLAYER NAME - WelcomeFragment */

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

    /** CATEGORY SELECTION - SelectCategoryFragment */

    fun getCategories(): Observable<AllCategory> {
        val job = repository.getCategories()

        Log.d("getCategories", "getCategories: ${job.subscribe()}")

        return job
    }

}
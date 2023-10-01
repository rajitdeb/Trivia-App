package com.rajit.triviaapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rajit.triviaapp.data.enum.GameDifficulty
import com.rajit.triviaapp.data.network.RetrofitInstance
import com.rajit.triviaapp.data.network.api.ApiService
import com.rajit.triviaapp.data.network.model.AllCategory
import com.rajit.triviaapp.data.network.model.AllQuestions
import com.rajit.triviaapp.data.network.model.Question
import com.rajit.triviaapp.data.repository.Repository
import com.rajit.triviaapp.util.Utils
import io.reactivex.rxjava3.core.Observable

class MainViewModel: ViewModel() {

    private val triviaApi: ApiService = RetrofitInstance().apiService
    private val repository: Repository = Repository(triviaApi)

    /** PLAYER NAME - WelcomeFragment */

    private var _playerName = ""
    val playerName get(): String = _playerName


    fun setPlayerName(name: String): Boolean {
        val isValid = Utils.validatePlayerNameEdt(name)
        if(isValid) {
            _playerName = Utils.convertToNormalCase(name)
            Log.d("MainViewModel", "setPlayerName: PlayerName: $_playerName")
            return true
        } else {
            return false
        }
    }

    /** CATEGORY SELECTION - SelectCategoryFragment */

    fun getCategories(): Observable<AllCategory> {
        return repository.getCategories()
    }

    /** SELECT GAME DIFFICULTY LEVEL - SelectGameDifficultyFragment */
    private var selectedGameDifficulty: GameDifficulty = GameDifficulty.EASY

    fun getSelectedGameDifficultyLevel(): GameDifficulty {
        return selectedGameDifficulty
    }

    fun setSelectedGameDifficultyLevel(level: GameDifficulty) {
        selectedGameDifficulty = level
    }


    /** GET ALL QUIZZES - QuizFragment */
    private var isSuccess: Boolean = true

    fun getResponseCode(): Boolean {
        return isSuccess
    }

    fun setResponseCode(value: Boolean) {
        isSuccess = value
    }

    private var questions: List<Question> = mutableListOf<Question>()

    fun getQuestions(categoryId: Int, difficulty: String): Observable<AllQuestions> {
        return repository.getQuestions(categoryId, difficulty)
    }

    fun setQuestions(list: List<Question>) {
        questions = list
    }

}
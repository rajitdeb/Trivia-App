package com.rajit.triviaapp.viewmodel

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
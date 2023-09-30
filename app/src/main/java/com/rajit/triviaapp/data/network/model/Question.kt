package com.rajit.triviaapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Question(
    val category: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    val difficulty: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>,
    val question: String,
    val type: String
)
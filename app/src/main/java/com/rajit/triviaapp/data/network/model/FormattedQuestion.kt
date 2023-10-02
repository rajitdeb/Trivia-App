package com.rajit.triviaapp.data.network.model

import com.google.gson.annotations.SerializedName

data class FormattedQuestion(
    val category: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    val difficulty: String,
    val question: String,
    val type: String,
    val options: List<String>
)

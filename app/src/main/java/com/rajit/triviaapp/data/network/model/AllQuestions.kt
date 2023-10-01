package com.rajit.triviaapp.data.network.model

import com.google.gson.annotations.SerializedName

data class AllQuestions(
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("results")
    val questions: List<Question>
)
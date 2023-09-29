package com.rajit.triviaapp.data.network.model

import com.google.gson.annotations.SerializedName

data class AllCategory(
    @SerializedName("trivia_categories")
    val categories: List<TriviaCategory>
)

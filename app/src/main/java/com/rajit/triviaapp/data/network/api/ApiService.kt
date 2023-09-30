package com.rajit.triviaapp.data.network.api

import com.rajit.triviaapp.data.network.model.AllCategory
import com.rajit.triviaapp.data.network.model.AllQuestions
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api_category.php")
    fun getCategories(): Observable<AllCategory>

    @GET("/api.php")
    fun getQuestions(
        @Query("amount") amount: Int = 15,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String = "multiple"
    ): Observable<AllQuestions>

}
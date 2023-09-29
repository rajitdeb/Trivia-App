package com.rajit.triviaapp.data.network.api

import com.rajit.triviaapp.data.network.model.AllCategory
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/api_category.php")
    fun getCategories(): Observable<AllCategory>

}
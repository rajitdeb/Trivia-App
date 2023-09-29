package com.rajit.triviaapp.data.repository

import com.rajit.triviaapp.data.network.api.ApiService
import com.rajit.triviaapp.data.network.model.AllCategory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository (
    private val triviaApi: ApiService
) {

    fun getCategories(): Observable<AllCategory> {
        return triviaApi.getCategories().subscribeOn(Schedulers.io())
    }

}
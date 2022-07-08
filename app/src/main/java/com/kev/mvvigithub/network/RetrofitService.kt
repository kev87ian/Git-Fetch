package com.kev.mvvigithub.network

import com.kev.mvvigithub.model.RepositoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call <RepositoryList>
}
package com.shashank.ekacareapp.data.remote.api

import com.shashank.ekacareapp.data.remote.models.response.NewResponseDTO
import com.shashank.ekacareapp.utils.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewResponseDTO
}
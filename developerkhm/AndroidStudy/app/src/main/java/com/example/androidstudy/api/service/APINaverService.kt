package com.example.androidstudy.api.service

import com.example.androidstudy.api.data.TotalModel
import com.example.androidstudy.common.CLIENT_ID
import com.example.androidstudy.common.CLIENT_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APINaverService {
    @Headers("X-Naver-Client-Id: $CLIENT_ID", "X-Naver-Client-Secret: $CLIENT_KEY")
    @GET("v1/search/{type}.json")
    fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Call<TotalModel>
}
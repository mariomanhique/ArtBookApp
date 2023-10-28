package com.mariomanhique.artbookapp.network

import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.util.Constants.API_KEY
import com.mariomanhique.artbookapp.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface ImagesApi {


    @Singleton
    @GET("api/")
    suspend fun searchImage(
       @Query("q") searchQuery: String,
       @Query("key") apiKey: String = API_KEY
    ): Response<ImageResponse>
}
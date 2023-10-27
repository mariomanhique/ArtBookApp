package com.mariomanhique.artbookapp.network

import retrofit2.http.GET
import javax.inject.Singleton

interface ImagesApi {


    @Singleton
    @GET("")
    suspend fun getImages(artName: String)
}
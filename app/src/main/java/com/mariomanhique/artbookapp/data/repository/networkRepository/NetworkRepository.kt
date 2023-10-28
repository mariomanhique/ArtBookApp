package com.mariomanhique.artbookapp.data.repository.networkRepository

import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.network.ImagesApi
import com.mariomanhique.artbookapp.util.Resource
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val imagesApi: ImagesApi):NetworkRepositoryInterface {
    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
        return  try {
           val response = imagesApi.searchImage(searchQuery = searchQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        } catch (e:Exception){
            Resource.error("Error searching the image",null)
        }
    }


}
package com.mariomanhique.artbookapp.repository

import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepositoryInterface
import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.util.Resource

class FakeNetworkRepository: NetworkRepositoryInterface {
    override suspend fun searchImage(searchQuery: String): Resource<ImageResponse> {
       return  Resource.success(ImageResponse(listOf(),0,0))
    }

}
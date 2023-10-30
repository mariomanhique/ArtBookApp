package com.mariomanhique.artbookapp.data.repository.networkRepository

import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.util.Resource

interface NetworkRepositoryInterface {

    suspend fun searchImage(
        searchQuery: String,
    ): Resource<ImageResponse>

}
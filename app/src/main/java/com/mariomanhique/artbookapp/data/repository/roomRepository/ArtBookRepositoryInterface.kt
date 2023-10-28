package com.mariomanhique.artbookapp.data.repository.roomRepository

import androidx.lifecycle.LiveData
import com.mariomanhique.artbookapp.model.Art

interface ArtBookRepositoryInterface {
    fun getAllArts(): LiveData<List<Art>>

    fun getArtById(artId: Int): LiveData<Art>

    suspend fun updateArt(art: Art)

    suspend fun deleteArts(art: Art)

    suspend fun deleteArtBookByID(artId: Int)

}
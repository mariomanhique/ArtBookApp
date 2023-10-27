package com.mariomanhique.artbookapp.data.repository

import com.mariomanhique.artbookapp.data.ArtBookDao
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import com.mariomanhique.artbookapp.model.ArtBook
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtBookRepository @Inject constructor(private val artBookDatabase: ArtBookDao) {
     fun getAllArts(): Flow<List<ArtBook>> {
        return artBookDatabase.getAllArts()
    }

     fun getArtById(artId: Int): Flow<ArtBook> {
        return artBookDatabase.getArtById(artId = artId)
    }

     suspend fun updateArt(artBook: ArtBook) {
        artBookDatabase.updateArt(artBook = artBook)
    }

     suspend fun deleteArts(artBook: ArtBook) {
        return artBookDatabase.deleteArts(artBook = artBook)
    }

     suspend fun deleteArtBookByID(artId: Int) {
        artBookDatabase.deleteArtBookByID(artId = artId)
    }


}
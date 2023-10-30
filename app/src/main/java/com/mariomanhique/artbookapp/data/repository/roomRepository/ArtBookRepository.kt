package com.mariomanhique.artbookapp.data.repository.roomRepository

import androidx.lifecycle.LiveData
import com.mariomanhique.artbookapp.data.ArtBookDao
import com.mariomanhique.artbookapp.model.Art
import javax.inject.Inject

class ArtBookRepository @Inject constructor(private val artBookDatabase: ArtBookDao):
    ArtBookRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artBookDatabase.insertArt(art = art)
    }

    override fun getAllArts(): LiveData<List<Art>> {
        return artBookDatabase.getAllArts()
    }

    override fun getArtById(artId: Int): LiveData<Art> {
        return artBookDatabase.getArtById(artId = artId)
    }

    override suspend fun updateArt(art: Art) {
        artBookDatabase.updateArt(art = art)
    }

    override suspend fun deleteArt(art: Art) {
        return artBookDatabase.deleteArts(art = art)
    }

    override suspend fun deleteArtBookByID(artId: Int) {
        artBookDatabase.deleteArtBookByID(artId = artId)
    }


}
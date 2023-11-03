package com.mariomanhique.artbookapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mariomanhique.artbookapp.data.repository.roomRepository.ArtRepositoryInterface
import com.mariomanhique.artbookapp.model.Art

class FakeArtRepository: ArtRepositoryInterface {

    private val arts = mutableListOf<Art>()
    private val artsLivedata = MutableLiveData<List<Art>>(arts)
    override suspend fun insertArt(art: Art) {
        arts.add(art)
    }

    override fun getAllArts(): LiveData<List<Art>> {
       return artsLivedata
    }

    private fun refreshData(){
        artsLivedata.postValue(arts)
    }

    override fun getArtById(artId: Int): LiveData<Art> {
        TODO("Not yet implemented")
    }

    override suspend fun updateArt(art: Art) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
    }

    override suspend fun deleteArtBookByID(artId: Int) {
        TODO("Not yet implemented")
    }

}
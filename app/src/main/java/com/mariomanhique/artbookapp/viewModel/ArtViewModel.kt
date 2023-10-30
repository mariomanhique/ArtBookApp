package com.mariomanhique.artbookapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepository
import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepositoryInterface
import com.mariomanhique.artbookapp.data.repository.roomRepository.ArtBookRepositoryInterface
import com.mariomanhique.artbookapp.model.Art
import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.util.Resource
import com.mariomanhique.artbookapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Year
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val artRepository: ArtBookRepositoryInterface): ViewModel() {

    val artList = artRepository.getAllArts()

    private var  _insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMsg: LiveData<Resource<Art>>
        get() =_insertArtMsg

    fun resetInsertArtMsg(){
        _insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun deleteArt(art: Art){
        viewModelScope.launch {
            artRepository.deleteArt(art = art)
        }
    }
    fun makeArtValidation(
        name:String,
        artistName: String,
        artImage: String,
        year: String
    ){
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty() || artImage.isEmpty()){
            _insertArtMsg.postValue(Resource.error("Fields cant be blank",null))
            return
        }

        val yearInt = try {
            year.toInt()
        }catch (e: Exception){
            _insertArtMsg.postValue(Resource.error("Year should be a number", null))
            return
        }
        val art = Art(name = name, artistName = artistName, artImage = artImage, year = yearInt)
        insertArt(art = art)
//        TODO()  Dont forget to setSelectedImage to empty string in the activity
        _insertArtMsg.postValue(Resource.success(art))
    }
    fun insertArt(art: Art){
        viewModelScope.launch {
            artRepository.insertArt(art = art)
        }
    }
}
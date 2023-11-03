package com.mariomanhique.artbookapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariomanhique.artbookapp.data.repository.roomRepository.ArtRepositoryInterface
import com.mariomanhique.artbookapp.model.Art
import com.mariomanhique.artbookapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val artRepository: ArtRepositoryInterface): ViewModel() {

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
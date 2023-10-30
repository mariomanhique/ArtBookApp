package com.mariomanhique.artbookapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepositoryInterface
import com.mariomanhique.artbookapp.model.Art
import com.mariomanhique.artbookapp.model.ImageResponse
import com.mariomanhique.artbookapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val networkRepository: NetworkRepositoryInterface) :ViewModel() {


    private var _images = MutableLiveData<Resource<ImageResponse>>()
    val images:LiveData<Resource<ImageResponse>>
        get() = _images

    private var _selectedImage = MutableLiveData<String>()
    val selectedImage: LiveData<String>
        get() = _selectedImage

    fun setSelectedImage(imageUrl: String){
        _selectedImage.postValue(imageUrl)
    }
    init {

    }

    fun searchImage(searchQuery: String) {
        if (searchQuery.isEmpty()) {
            return
        }
        _images.value = Resource.loading(null)

        viewModelScope.launch {
            try {
                val result = networkRepository.searchImage(searchQuery = searchQuery)
                //When we r working with live data we can use .postValue or .value for assigning,
                //But for testing purposes, we have to use .value because it notifies all the observers immediately
                _images.value = result
            } catch (e: Exception) {
                _images.value = Resource.error(e.message.toString(),null)
            }

        }
    }

}
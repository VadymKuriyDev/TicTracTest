package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ImagePreviewViewModel @Inject constructor(): ViewModel() {

    private val logoLiveData: MutableLiveData<String> = MutableLiveData()

    fun loadData(logo: String){
        logoLiveData.postValue(logo)
    }

    fun getObservableUserData(): LiveData<String> = logoLiveData
}

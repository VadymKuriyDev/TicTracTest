package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tictracapp.data.UsersRepository
import javax.inject.Inject

class ImagePreviewViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    var userId: Int = -1
        set(value) {
            if (field != value){
                logoLiveData = usersRepository.getUserLogo(value)
            }
            field = value
        }
    private lateinit var logoLiveData: LiveData<String>

    fun getObservableUserLogo(): LiveData<String> = logoLiveData
}

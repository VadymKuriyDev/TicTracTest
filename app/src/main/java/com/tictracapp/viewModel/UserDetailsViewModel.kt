package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tictracapp.data.UsersRepository
import com.tictracapp.data.model.UserDetailsData
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val usersRepository: UsersRepository): ViewModel() {

    var userId: Int = -1
        set(value) {
            if (field != value){
                userLiveData = usersRepository.getUserDetails(value)
            }
            field = value
    }

    private lateinit var userLiveData: LiveData<UserDetailsData>

    fun getObservableUserData(): LiveData<UserDetailsData> = userLiveData
}

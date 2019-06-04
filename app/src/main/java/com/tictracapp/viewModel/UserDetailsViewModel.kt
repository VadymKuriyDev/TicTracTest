package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tictracapp.data.model.User
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(): ViewModel() {

    private val userLiveData: MutableLiveData<User> = MutableLiveData()

    fun loadData(user: User){
        userLiveData.postValue(user)
    }

    fun getObservableUserData(): LiveData<User> = userLiveData

    fun getUserData(): User? = userLiveData.value
}

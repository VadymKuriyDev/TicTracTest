package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tictracapp.data.UsersRepository
import com.tictracapp.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData(){
        compositeDisposable.add(
            usersRepository.loadUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    Timber.d("Loaded ${items.size} users")
                    usersLiveData.value = items
                }, { throwable ->
                    Timber.e("Load error ${throwable.message} ")
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getObservableUsersData(): LiveData<List<User>> = usersLiveData
}

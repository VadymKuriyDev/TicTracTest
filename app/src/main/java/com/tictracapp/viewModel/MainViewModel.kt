package com.tictracapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tictracapp.data.UsersRepository
import com.tictracapp.data.model.UserListItemData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(usersRepository: UsersRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val usersLiveData: LiveData<List<UserListItemData>> = usersRepository.getUsers()

    init {
        compositeDisposable.add(
            usersRepository.loadUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("New data loaded")
                }, { throwable ->
                    Timber.e("Load error ${throwable.message} ")
                })
        )
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    fun getObservableUsersData(): LiveData<List<UserListItemData>> = usersLiveData
}

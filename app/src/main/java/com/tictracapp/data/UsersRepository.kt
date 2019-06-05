package com.tictracapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tictracapp.api.CommonApiInterface
import com.tictracapp.data.db.UserDao
import com.tictracapp.data.model.UserDetailsData
import com.tictracapp.data.model.UserListItemData
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val userDao: UserDao,
    private val commonApiInterface: CommonApiInterface) {

    fun loadUsers(): Completable {
        return commonApiInterface.getUsersList(USERS_URL)
            .subscribeOn(Schedulers.io())
            .map { UsersMapping.fromApiToModel(it) }
            .flatMapCompletable { entities ->
                return@flatMapCompletable CompletableSource { userDao.updateData(entities) }
            }
    }


    fun getUsers(): LiveData<List<UserListItemData>> {
        return userDao.getUsersList()
    }

    fun getUserDetails(userId: Int): LiveData<UserDetailsData> {
        return userDao.getUserById(userId)
    }

    fun getUserLogo(userId: Int): LiveData<String> {
        return userDao.getLogoById(userId)
    }

    companion object{
        const val USERS_URL = "http://media.tictrac.com/tmp/users.json"
    }
}
package com.tictracapp.data

import com.tictracapp.api.CommonApiInterface
import com.tictracapp.data.model.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val commonApiInterface: CommonApiInterface) {

    fun loadUsers(): Single<List<User>> {
        return commonApiInterface.getUsersList(USERS_URL)
            .subscribeOn(Schedulers.io())
            .map { UsersMapping.fromApiToModel(it) }
    }

    companion object{
        const val USERS_URL = "http://media.tictrac.com/tmp/users.json"
    }
}
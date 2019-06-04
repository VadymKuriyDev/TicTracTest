package com.tictracapp.data

import com.tictracapp.api.response.UserApiEntity
import com.tictracapp.data.model.User

object UsersMapping {

    fun fromApiToModel(usersApi: List<UserApiEntity>): List<User> {
        return usersApi.map{ fromApiToModel(it) }
    }

    private fun fromApiToModel(entity: UserApiEntity): User =
        User(entity.profile_picture,
            entity.email,
            entity.name,
            entity.infos)
}
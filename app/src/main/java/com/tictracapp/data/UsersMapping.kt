package com.tictracapp.data

import com.tictracapp.api.response.UserApiEntity
import com.tictracapp.data.db.UserEntity

object UsersMapping {

    fun fromApiToModel(usersApi: List<UserApiEntity>): List<UserEntity> {
        return usersApi.map { fromApiToModel(it) }
    }

    private fun fromApiToModel(entity: UserApiEntity): UserEntity =
        UserEntity(
            profilePicture = entity.profile_picture,
            email = entity.email,
            name = entity.name,
            phone = entity.infos
        )
}
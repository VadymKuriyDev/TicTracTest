package com.tictracapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val profilePicture: String,
    val email:String,
    val name:String,
    val phone:String
)
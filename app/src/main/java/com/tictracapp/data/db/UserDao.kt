package com.tictracapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.tictracapp.data.model.UserDetailsData
import com.tictracapp.data.model.UserListItemData

@Dao
abstract class UserDao {

    @Transaction
    open fun updateData(users: List<UserEntity>) {
        deleteAllUsers()
        insertAll(users)
    }
    @Insert
    abstract fun insertAll(users: List<UserEntity>)
    @Query("DELETE FROM Users")
    abstract fun deleteAllUsers()

    @Query("SELECT id, name, profilePicture FROM USERS")
    abstract fun getUsersList(): LiveData<List<UserListItemData>>

    @Query("SELECT id, profilePicture, email, name, phone FROM USERS WHERE id == :userId")
    abstract fun getUserById(userId: Int): LiveData<UserDetailsData>

    @Query("SELECT profilePicture FROM USERS WHERE id == :id")
    abstract fun getLogoById(id: Int): LiveData<String>

    @Query("SELECT * FROM USERS")
    abstract fun getUsers(): List<UserEntity>

}
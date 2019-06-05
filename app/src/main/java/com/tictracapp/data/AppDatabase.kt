package com.tictracapp.data

import androidx.room.RoomDatabase
import androidx.room.Database
import com.tictracapp.data.db.UserDao
import com.tictracapp.data.db.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "TicTrack.db"
    }
}
package com.jedrzejblaszczak.githubusers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jedrzejblaszczak.githubusers.data.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

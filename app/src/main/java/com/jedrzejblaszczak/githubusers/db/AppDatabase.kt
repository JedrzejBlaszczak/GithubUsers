package com.jedrzejblaszczak.githubusers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jedrzejblaszczak.githubusers.db.user.UserDao
import com.jedrzejblaszczak.githubusers.db.user.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

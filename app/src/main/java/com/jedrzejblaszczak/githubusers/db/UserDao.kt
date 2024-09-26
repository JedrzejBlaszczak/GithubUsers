package com.jedrzejblaszczak.githubusers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jedrzejblaszczak.githubusers.data.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserModel>)

    @Query("SELECT * FROM users WHERE login LIKE :query")
    suspend fun searchUsers(query: String): List<UserModel>
}

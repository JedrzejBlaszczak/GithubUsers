package com.jedrzejblaszczak.githubusers.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserModel>)

    @Query("SELECT * FROM users WHERE login LIKE '%' || :query || '%'")
    suspend fun searchUsersByLogin(query: String): List<UserModel>

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun searchUserById(id: Int): UserModel?
}

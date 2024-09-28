package com.jedrzejblaszczak.githubusers.repository

import android.util.Log
import com.jedrzejblaszczak.githubusers.api.UsersApiService
import com.jedrzejblaszczak.githubusers.db.user.UserDao
import com.jedrzejblaszczak.githubusers.db.user.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(
    private val apiService: UsersApiService,
    private val userDao: UserDao
) {
    suspend fun getUsers(): Flow<List<UserModel>> = flow {
        emit(userDao.getAllUsers())
        try {
            val users = apiService.fetchUsers()
            userDao.insertUsers(users)
            emit(users)
        } catch (e: Exception) {
            Log.e(TAG, "getUsers failed: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUsersByLoginPattern(query: String): Flow<List<UserModel>> = flow {
        emit(userDao.searchUsersByLogin(query))
    }.flowOn(Dispatchers.IO)

    suspend fun getUserById(userId: Int): Flow<UserModel?> = flow {
        emit(userDao.searchUserById(userId))
    }.flowOn(Dispatchers.IO)

    companion object {
        val TAG = UserRepository::class.simpleName
    }
}

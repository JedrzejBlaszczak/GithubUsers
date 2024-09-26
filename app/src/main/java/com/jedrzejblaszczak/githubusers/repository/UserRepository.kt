package com.jedrzejblaszczak.githubusers.repository

import android.util.Log
import com.jedrzejblaszczak.githubusers.api.UsersApiService
import com.jedrzejblaszczak.githubusers.data.UserModel
import com.jedrzejblaszczak.githubusers.db.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(
    private val apiService: UsersApiService,
    private val userDao: UserDao
) {
    fun getUsers(): Flow<List<UserModel>> = flow {
        emit(userDao.getAllUsers())
        try {
            val users = apiService.fetchUsers()
            userDao.insertUsers(users)
            emit(users)
        } catch (e: Exception) {
            Log.e(UserRepository::class.simpleName, "getUsers failed: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}

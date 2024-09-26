package com.jedrzejblaszczak.githubusers.repository

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

            val users = apiService.fetchUsers()
            userDao.insertUsers(users)

        emit(userDao.getAllUsers())
    }.flowOn(Dispatchers.IO)
}

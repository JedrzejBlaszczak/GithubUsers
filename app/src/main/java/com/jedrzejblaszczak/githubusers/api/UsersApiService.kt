package com.jedrzejblaszczak.githubusers.api

import com.jedrzejblaszczak.githubusers.db.user.UserModel

interface UsersApiService {
    suspend fun fetchUsers(): List<UserModel>
}

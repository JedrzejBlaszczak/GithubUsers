package com.jedrzejblaszczak.githubusers.api

import com.jedrzejblaszczak.githubusers.data.UserModel

interface UsersApiService {
    suspend fun fetchUsers(since: Int = 0): List<UserModel>
}

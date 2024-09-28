package com.jedrzejblaszczak.githubusers.api

import com.jedrzejblaszczak.githubusers.db.user.UserModel

interface UsersApiService {
    suspend fun fetchUsers(since: Int = 0): List<UserModel>
}

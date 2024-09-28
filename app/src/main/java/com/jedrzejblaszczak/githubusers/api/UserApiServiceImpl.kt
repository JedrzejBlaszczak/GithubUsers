package com.jedrzejblaszczak.githubusers.api

import com.jedrzejblaszczak.githubusers.db.user.UserModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserApiServiceImpl(private val client: HttpClient) : UsersApiService {
    override suspend fun fetchUsers(): List<UserModel> {
        return client.get("https://api.github.com/users").body()
    }
}

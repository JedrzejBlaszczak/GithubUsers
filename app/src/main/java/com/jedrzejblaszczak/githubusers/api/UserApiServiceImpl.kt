package com.jedrzejblaszczak.githubusers.api

import com.jedrzejblaszczak.githubusers.db.user.UserModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UserApiServiceImpl(private val client: HttpClient) : UsersApiService {
    override suspend fun fetchUsers(since: Int): List<UserModel> {
        return client.get("https://api.github.com/users") {
            parameter("since", since)
            parameter("per_page", 20)
        }.body()
    }
}

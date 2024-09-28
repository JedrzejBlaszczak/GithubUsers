package com.jedrzejblaszczak.githubusers.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey val id: Int,
    val login: String,
    val avatar_url: String,
    val url: String,
    val node_id: String,
    val type: String,
    val site_admin: Boolean,
) : java.io.Serializable

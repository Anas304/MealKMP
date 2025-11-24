package org.example.project.chat.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val title: String,
    val body: String,
)


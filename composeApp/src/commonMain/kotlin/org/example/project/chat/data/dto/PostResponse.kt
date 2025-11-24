package org.example.project.chat.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: String,
    val title: String,
    val body: String,
)

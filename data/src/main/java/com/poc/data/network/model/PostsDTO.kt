package com.poc.data.network.model

data class PostsDTO(
    val `data`: List<PostDTO>?,
    val limit: Int?,
    val page: Int?,
    val total: Int?
)
package com.poc.data.network.model

data class PostDTO(
    val id: String?,
    val image: String?,
    val likes: Int?,
    val owner: OwnerDTO?,
    val publishDate: String?,
    val tags: List<String>?,
    val text: String?
)
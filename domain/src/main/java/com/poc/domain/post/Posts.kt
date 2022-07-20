package com.poc.domain.model.post

data class Posts(
    val `data`: List<Post>,
    val limit: Int,
    val page: Int,
    val total: Int
)
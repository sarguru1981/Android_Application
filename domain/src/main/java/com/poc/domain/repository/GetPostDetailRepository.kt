package com.poc.domain.repository

import com.poc.domain.model.post.Post

interface GetPostDetailRepository {
    suspend fun getPostDetails(id: String): Post
}
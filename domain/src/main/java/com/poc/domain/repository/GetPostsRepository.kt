package com.poc.domain.repository

import com.poc.domain.model.post.Post
import kotlinx.coroutines.flow.Flow

interface GetPostsRepository {

    suspend fun getPosts() : Flow<List<Post>>
}
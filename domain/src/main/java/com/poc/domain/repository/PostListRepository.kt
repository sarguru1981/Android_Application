package com.poc.domain.repository

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import kotlinx.coroutines.flow.Flow

interface PostListRepository {

    suspend fun getPosts(): Flow<Output<List<Post>>>
}
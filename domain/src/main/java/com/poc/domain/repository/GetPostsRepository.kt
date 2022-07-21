package com.poc.domain.repository

import com.poc.domain.model.post.Post

interface GetPostsRepository {

    suspend fun getPosts() : List<Post>
}
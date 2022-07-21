package com.poc.data.network.repository.post.datasource

import com.poc.domain.model.post.Post

interface PostRemoteDatasource {
   suspend fun getPosts(): List<Post>
}
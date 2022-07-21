package com.poc.data.network.repository.post.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post


interface PostCacheDataSource {
    suspend fun getPostFromCache(): List<Post>
    suspend fun savePostsToCache(posts: List<Post>)
}
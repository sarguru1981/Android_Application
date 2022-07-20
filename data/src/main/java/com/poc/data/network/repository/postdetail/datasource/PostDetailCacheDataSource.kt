package com.poc.data.network.repository.postdetail.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post


interface PostDetailCacheDataSource {
    suspend fun getPostDetailFromCache(): Post
    suspend fun savePostDetailToCache(post:Post)
}
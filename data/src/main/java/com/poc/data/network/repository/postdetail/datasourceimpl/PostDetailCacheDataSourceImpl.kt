package com.poc.data.network.repository.postdetail.datasourceimpl

import com.poc.common.Resource
import com.poc.data.network.repository.postdetail.datasource.PostDetailCacheDataSource
import com.poc.domain.model.post.Post

class PostDetailCacheDataSourceImpl :
    PostDetailCacheDataSource {
    private lateinit var post : Post

    override suspend fun getPostDetailFromCache(): Post {
        return post
    }

    override suspend fun savePostDetailToCache(post: Post) {
        this.post = post
    }
}
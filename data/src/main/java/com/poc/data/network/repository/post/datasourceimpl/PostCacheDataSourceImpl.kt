package com.poc.data.network.repository.post.datasourceimpl

import com.poc.common.Resource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.domain.model.post.Post

class PostCacheDataSourceImpl :
    PostCacheDataSource {
    private lateinit var postList : Resource<List<Post>>

    override suspend fun getPostFromCache(): Resource<List<Post>> {
        return postList
    }

    override suspend fun savePostsToCache(posts: Resource<List<Post>>) {
        postList = posts
    }
}
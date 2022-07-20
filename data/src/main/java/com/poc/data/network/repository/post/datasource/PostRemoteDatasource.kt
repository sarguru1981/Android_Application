package com.poc.data.network.repository.post.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post

interface PostRemoteDatasource {
   //suspend fun getPosts(): Response<PostsDTO>
   suspend fun getPagerPosts(page: Int, limit: Int): Resource<List<Post>>
}
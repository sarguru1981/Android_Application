package com.poc.data.network.repository.post.datasource

import com.poc.data.network.model.PostsDTO
import com.poc.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PostRemoteDatasource {
   suspend fun getPosts(): Flow<List<Post>>
}
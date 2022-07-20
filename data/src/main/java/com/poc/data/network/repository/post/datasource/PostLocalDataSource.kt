package com.poc.data.network.repository.post.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post


interface PostLocalDataSource {
  suspend fun getPostFromDB(): Resource<List<Post>>
  suspend fun savePostToDB(posts: Resource<List<Post>>)
  suspend fun clearAll()
}
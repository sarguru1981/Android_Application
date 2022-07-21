package com.poc.data.network.repository.post.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post


interface PostLocalDataSource {
  suspend fun getPostFromDB(): List<Post>
  suspend fun savePostToDB(posts: List<Post>)
  suspend fun clearAll()
}
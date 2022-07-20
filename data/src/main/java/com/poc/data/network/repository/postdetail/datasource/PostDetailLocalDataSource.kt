package com.poc.data.network.repository.postdetail.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post


interface PostDetailLocalDataSource {
  suspend fun getPostDetailFromDB(id: String): Post
  suspend fun savePostDetailToDB(posts: Post)
  suspend fun clearPostItem(id: String)
}
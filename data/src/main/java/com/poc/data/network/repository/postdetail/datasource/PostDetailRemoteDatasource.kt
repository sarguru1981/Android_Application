package com.poc.data.network.repository.postdetail.datasource

import com.poc.common.Resource
import com.poc.domain.model.post.Post

interface PostDetailRemoteDatasource {
   suspend fun getPostDetails(id: String): Post
}
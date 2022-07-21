package com.poc.data.network.repository.postdetail.datasource

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post

interface PostDetailRemoteDatasource {
   suspend fun getPostDetails(id: String): Output<Post>
}
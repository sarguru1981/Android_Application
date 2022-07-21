package com.poc.data.network.repository.post.datasource

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post

interface PostRemoteDatasource {
   suspend fun getPosts(): Output<List<Post>>
}
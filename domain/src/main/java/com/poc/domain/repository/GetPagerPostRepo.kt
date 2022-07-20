package com.poc.domain.repository

import com.poc.common.Resource
import com.poc.domain.model.post.Post

interface GetPagerPostRepo {
    suspend fun getPagerPosts(page: Int, limit: Int): Resource<List<Post>>
}
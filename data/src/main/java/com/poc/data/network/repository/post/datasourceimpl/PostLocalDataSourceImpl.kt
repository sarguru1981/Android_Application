package com.poc.data.network.repository.post.datasourceimpl

import com.poc.common.Resource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.room.post.PostDAO
import com.poc.domain.model.post.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(private val postDAO:PostDAO):
    PostLocalDataSource {
    override suspend fun getPostFromDB(): Resource<List<Post>> {
       return Resource.Success(postDAO.getPosts())
    }

    override suspend fun savePostToDB(posts: Resource<List<Post>>) {
        CoroutineScope(Dispatchers.IO).launch {
            posts.data?.let { postDAO.insertAllPosts(it) }
        }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           postDAO.deleteAllItems()
       }
    }
}
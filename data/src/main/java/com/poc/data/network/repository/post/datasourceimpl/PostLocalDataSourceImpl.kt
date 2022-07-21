package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.room.post.PostDAO
import com.poc.domain.model.post.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(private val postDAO:PostDAO):
    PostLocalDataSource {
    override suspend fun getPostFromDB(): List<Post> {
       return postDAO.getPosts()
    }

    override suspend fun savePostToDB(posts: List<Post>) {
        CoroutineScope(Dispatchers.IO).launch {
            postDAO.insertAllPosts(posts)
        }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           postDAO.deleteAllItems()
       }
    }
}
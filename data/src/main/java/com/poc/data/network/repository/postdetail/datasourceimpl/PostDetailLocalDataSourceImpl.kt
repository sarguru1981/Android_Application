package com.poc.data.network.repository.postdetail.datasourceimpl

import com.poc.common.Resource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailLocalDataSource
import com.poc.data.room.post.PostDAO
import com.poc.domain.model.post.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDetailLocalDataSourceImpl @Inject constructor(private val postDAO:PostDAO):
    PostDetailLocalDataSource {
    override suspend fun getPostDetailFromDB(id: String): Post {
       return postDAO.getPostItem(id)
    }

    override suspend fun savePostDetailToDB(post: Post) {
        CoroutineScope(Dispatchers.IO).launch {
            postDAO.insertPostItem(post)
        }
    }

    override suspend fun clearPostItem(id: String) {
       CoroutineScope(Dispatchers.IO).launch {
           postDAO.deletePostItem(id)
       }
    }
}
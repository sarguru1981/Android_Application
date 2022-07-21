package com.poc.data.network.repository.post

import com.poc.common.Resource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.utils.SafeApiRequest
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostsRepository
import javax.inject.Inject

class GetPostsRepositoryImpl @Inject constructor(
    private val getPostRemoteDatasource: PostRemoteDatasource,
    private val getPostLocalDataSource: PostLocalDataSource,
    private val getPostCacheDataSource: PostCacheDataSource
) :
    GetPostsRepository,
    SafeApiRequest() {
    override suspend fun getPosts(): List<Post> {
        return getPostFromRemote()
    }

    private suspend fun getPostFromRemote(): List<Post> {
        lateinit var postItem: List<Post>
        try {
            postItem = getPostRemoteDatasource.getPosts()
            Resource.Success(postItem)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        return postItem
    }

    private suspend fun getPostFromDB(): List<Post> {
        lateinit var posts: List<Post>
        try {
            posts = getPostLocalDataSource.getPostFromDB()
            Resource.Success(posts)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (posts.isNotEmpty()) {
            return posts
        } else {
            posts = getPostFromRemote()
            getPostLocalDataSource.savePostToDB(posts)
        }
        return posts
    }

    private suspend fun getPostFromCache(): List<Post> {
        lateinit var posts: List<Post>
        try {
            posts = getPostCacheDataSource.getPostFromCache()
            Resource.Success(posts)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (posts.isNotEmpty()) {
            return posts
        } else {
            posts = getPostFromDB()
            getPostCacheDataSource.savePostsToCache(posts)
        }
        return posts
    }
}
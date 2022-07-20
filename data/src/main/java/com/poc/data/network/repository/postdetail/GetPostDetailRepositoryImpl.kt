package com.poc.data.network.repository.postdetail

import com.poc.common.Resource
import com.poc.data.network.repository.postdetail.datasource.PostDetailCacheDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailLocalDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostDetailRepository
import javax.inject.Inject

class GetPostDetailRepositoryImpl @Inject constructor(
    private val postDetailRemoteDatasource: PostDetailRemoteDatasource,
    private val postDetailLocalDataSource: PostDetailLocalDataSource,
    private val postDetailCacheDataSource: PostDetailCacheDataSource
) :
    GetPostDetailRepository{


    override suspend fun getPostDetails(id: String): Post {
        return getPostDetailFromRemote(id)
    }

    suspend fun getPostDetailFromRemote(id: String): Post{
        lateinit var postItem: Post
        try {
            postItem = postDetailRemoteDatasource.getPostDetails(id)
            Resource.Success(postItem)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        return postItem
    }

    suspend fun getPostDetailFromDB(id: String): Post {
        lateinit var postItem: Post
        try {
            postItem = postDetailLocalDataSource.getPostDetailFromDB(id)
            Resource.Success(postItem)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (postItem != null) {
            return postItem
        } else {
            postItem = getPostDetailFromRemote(id)
            postDetailLocalDataSource.savePostDetailToDB(postItem)
        }
        return postItem
    }

    suspend fun getPostDetailFromCache(id: String): Post {
        lateinit var postItem: Post
        try {
            postItem = postDetailCacheDataSource.getPostDetailFromCache()
            Resource.Success(postItem)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (postItem != null) {
            return postItem
        } else {
            postItem = getPostDetailFromDB(id)
            postDetailCacheDataSource.savePostDetailToCache(postItem)
        }

        return postItem
    }

}
package com.poc.data.network.repository.post

import com.poc.common.Resource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPagerPostRepo
import javax.inject.Inject

class GetPagerPostRepoImpl @Inject constructor(
    private val postRemoteDatasource: PostRemoteDatasource,
    private val postLocalDataSource: PostLocalDataSource,
    private val postCacheDataSource: PostCacheDataSource
) : GetPagerPostRepo {

    override suspend fun getPagerPosts(page: Int, limit: Int): Resource<List<Post>> {
        return getPostFromRemote(page, limit)
    }

    suspend fun getPostFromRemote(page: Int, limit: Int): Resource<List<Post>> {
        lateinit var postList: Resource<List<Post>>
        try {
            postList = postRemoteDatasource.getPagerPosts(page, limit)
            Resource.Success(postList)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        return postList
    }

    suspend fun getPostsFromDB(page: Int, limit: Int): Resource<List<Post>> {
        lateinit var postList: Resource<List<Post>>
        try {
            postList = postLocalDataSource.getPostFromDB()
            Resource.Success(postList)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (postList.data?.isNotEmpty() == true) {
            return postList
        } else {
            postList = getPostFromRemote(page, limit)
            postLocalDataSource.savePostToDB(postList)
        }
        return postList
    }

    suspend fun getPostsFromCache(page: Int, limit: Int): Resource<List<Post>> {
        lateinit var postList: Resource<List<Post>>
        try {
            postList = postCacheDataSource.getPostFromCache()
            Resource.Success(postList)
        } catch (exception: Exception) {
            Resource.Error(exception.message.toString())
        }
        if (postList.data?.isNotEmpty() == true) {
            return postList
        } else {
            postList = getPostsFromDB(page, limit)
            postCacheDataSource.savePostsToCache(postList)
        }

        return postList
    }
}
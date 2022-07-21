package com.poc.data.network.repository.post

import com.poc.data.network.repository.BaseRemoteDataSource
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.repository.PostListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class PostListRepositoryImpl @Inject constructor(
    private val postRemoteDatasource: PostRemoteDatasource,
    private val getPostLocalDataSource: PostLocalDataSource,
    private val getPostCacheDataSource: PostCacheDataSource,
    retrofit: Retrofit
) : PostListRepository, BaseRemoteDataSource(retrofit) {


    override suspend fun getPosts(): Flow<Output<List<Post>>> {
        return getPostsFromRemote()
    }

    private suspend fun getPostsFromRemote(): Flow<Output<List<Post>>> {
        return flow {
            emit(Output.loading())
            val result = postRemoteDatasource.getPosts()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getPostsFromDB(): Flow<Output<List<Post>>> {
        val posts: List<Post> = getPostLocalDataSource.getPostFromDB()
        return if (posts.isNotEmpty()) {
            flow {
                emit(Output.loading())
                val postList = getListResponse(
                    request = { posts }
                )
                emit(postList)
            }.flowOn(Dispatchers.IO)
        } else {
            val postList: Flow<Output<List<Post>>> = getPostsFromRemote()
            getPostLocalDataSource.savePostToDB(convertFlowListToList(postList))
            postList
        }
    }

    private suspend fun convertFlowListToList(postList: Flow<Output<List<Post>>>):  List<Post>{
        lateinit var posts: List<Post>
        postList.collect { result ->
            result.data.let { list ->
                posts = list!!
            }
        }
        return posts
    }

    private suspend fun getPostsFromCache(): Flow<Output<List<Post>>> {
        val posts: List<Post> = getPostCacheDataSource.getPostFromCache()
        return if (posts.isNotEmpty()) {
            flow {
                emit(Output.loading())
                val postList = getListResponse(
                    request = { posts }
                )
                emit(postList)
            }.flowOn(Dispatchers.IO)
        } else {
            val postList: Flow<Output<List<Post>>> = getPostsFromDB()
            getPostCacheDataSource.savePostsToCache(convertFlowListToList(postList))
            postList
        }
    }

}
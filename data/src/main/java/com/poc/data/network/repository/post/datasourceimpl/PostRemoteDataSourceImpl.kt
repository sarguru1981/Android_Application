package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.domain.model.post.Post
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) :
    PostRemoteDatasource {

    override suspend fun getPosts(): Flow<List<Post>> {
        val posts: Flow<List<Post>> = flow {
            while (true) {
                val posts = apiService.getPosts()
                emit(
                    posts.data?.toDomain() ?: emptyList()
                )// Emits the result of the request to the flow
                delay(5000L) // Suspends the coroutine for some time
            }
        }.flowOn(ioDispatcher)
        return posts
    }
}


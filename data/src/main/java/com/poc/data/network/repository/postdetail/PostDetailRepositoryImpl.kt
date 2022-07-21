package com.poc.data.network.repository.postdetail

import com.poc.data.network.repository.BaseRemoteDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.repository.PostDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class PostDetailRepositoryImpl @Inject constructor(
    private val postDetailRemoteDatasource: PostDetailRemoteDatasource,
    retrofit: Retrofit
) : PostDetailRepository, BaseRemoteDataSource(retrofit) {


    override suspend fun getPostDetails(id: String): Flow<Output<Post>> {
        return getPostDetailsFromRemote(id)
    }

    private suspend fun getPostDetailsFromRemote(id: String): Flow<Output<Post>> {
        return flow {
            emit(Output.loading())
            val result = postDetailRemoteDatasource.getPostDetails(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
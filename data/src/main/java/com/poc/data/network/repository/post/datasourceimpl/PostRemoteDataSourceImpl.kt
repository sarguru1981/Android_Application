package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.utils.SafeApiRequest
import com.poc.domain.model.post.Post
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) :
    PostRemoteDatasource, SafeApiRequest() {

    override suspend fun getPosts(): List<Post> {
        val response = safeApiRequest { apiService.getPosts() }
        return response.data ?: emptyList()
    }
}


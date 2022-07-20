package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.common.Resource
import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.model.PostDTO
import com.poc.domain.model.post.Post
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    PostRemoteDatasource {

    override suspend fun getPagerPosts(page: Int, limit: Int): Resource<List<Post>> {
        return try {
            val response = apiService.getPostPagination(page = page, limit = limit)
            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)
            } else {
                Resource.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}


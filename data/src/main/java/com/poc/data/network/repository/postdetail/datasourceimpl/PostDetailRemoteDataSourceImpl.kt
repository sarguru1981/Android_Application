package com.poc.data.network.repository.postdetail.datasourceimpl

import com.poc.data.ApiService
import com.poc.data.network.repository.BaseRemoteDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import retrofit2.Retrofit
import javax.inject.Inject

class PostDetailRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) :
    PostDetailRemoteDatasource, BaseRemoteDataSource(retrofit) {

    override suspend fun getPostDetails(id: String): Output<Post> {
        return getResponse(
            request = { apiService.getPostDetails(id = id) },
            defaultErrorMessage = "Error fetching Post"
        )
    }
}
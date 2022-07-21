package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.ApiService
import com.poc.data.network.repository.BaseRemoteDataSource
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import retrofit2.Retrofit
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) :
    PostRemoteDatasource, BaseRemoteDataSource(retrofit) {


    override suspend fun getPosts(): Output<List<Post>> {
        return getListResponse(
            request = { apiService.getPosts().body()?.data!! }
        )
    }
}
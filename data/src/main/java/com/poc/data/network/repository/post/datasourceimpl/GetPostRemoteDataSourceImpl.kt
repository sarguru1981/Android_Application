package com.poc.data.network.repository.post.datasourceimpl

import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.BaseRemoteDataSource
import com.poc.data.network.repository.post.datasource.GetPostRemoteDatasource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import retrofit2.Retrofit
import javax.inject.Inject

class GetPostRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) :
    GetPostRemoteDatasource, BaseRemoteDataSource(retrofit) {


    override suspend fun getPosts(): Output<List<Post>> {
        return getListResponse(
            request = { apiService.getPosts().body()?.data!! }
        )
    }
}
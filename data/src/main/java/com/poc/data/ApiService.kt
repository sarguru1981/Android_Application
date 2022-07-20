package com.poc.data

import com.poc.common.Constant
import com.poc.data.network.model.PostDTO
import com.poc.data.network.model.PostsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("post")
    suspend fun getPosts(
        @Header("app-id") appId: String = Constant.APP_ID
    ): PostsDTO

    @GET("post")
    suspend fun getPostList(
        @Header("app-id") appId: String = Constant.APP_ID
    ): Response<PostsDTO>

    @GET("post")
    suspend fun getPostPagination(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PostsDTO>

    @GET("post/{id}")
    suspend fun getPostDetails(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Path("id") id: String
    ): Response<PostDTO>
}
package com.poc.data

import com.poc.common.Constant
import com.poc.domain.model.post.Post
import com.poc.domain.model.post.Posts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("post")
    suspend fun getPosts(
        @Header("app-id") appId: String = Constant.APP_ID
    ): Response<Posts>

    @GET("post/{id}")
    suspend fun getPostDetails(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Path("id") id: String
    ): Response<Post>
}
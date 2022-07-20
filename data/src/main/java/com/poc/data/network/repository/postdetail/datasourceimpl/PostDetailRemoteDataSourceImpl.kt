package com.anushka.tmdbclient.data.repository.movie.datasourceImpl

import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.data.network.utils.SafeApiRequest
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostDetailRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) :
    PostDetailRemoteDatasource, SafeApiRequest() {

    // suspend functions for one-shot calls
    override suspend fun getPostDetails(id: String): Post {
        // Use the withContext() function from the coroutines library to move the execution of a coroutine to a different thread
        val response = withContext(ioDispatcher) {
            safeApiRequest { apiService.getPostDetails(id = id) }
        }

        val post = Post(
            id = response.id ?: "",
            image = response.image ?: "",
            likes = response.likes ?: 0,
            owner = response.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = response.publishDate ?: "",
            tags = response.tags ?: emptyList(),
            text = response.text ?: ""
        )
        return post
    }
}


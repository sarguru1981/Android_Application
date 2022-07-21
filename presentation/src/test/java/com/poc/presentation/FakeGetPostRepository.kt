package com.poc.presentation

import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeGetPostRepository @Inject constructor(private val apiService: ApiService) : GetPostsRepository {

    private val posts = mutableListOf<Post>()

    init {
        posts.add(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            )
        )
        posts.add(
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            )
        )
        posts.add(
            Post(
                id = "60d21b8667d0d8992e610d3f",
                image = "https://img.dummyapi.io/photo-1515376721779-7db6951da88d.jpg",
                likes = 16,
                tags = emptyList(),
                text = "@adventure.yuki frozen grass short-coated black dog sitting on snow",
                publishDate = "2020-05-24T05:44:55.297Z",
                owner = Owner("", "", "", "", "")
            )
        )
    }

    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts().body()?.data!!
    }

}
package com.poc.domain

import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post


fun getDummyPosts() = listOf(
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

fun getDummyPost() = Post(
    id = "60d21b4667d0d8992e610c85",
    image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
    likes = 43,
    tags = emptyList(),
    text = "adult Labrador retriever",
    publishDate = "2020-05-24T14:53:17.598Z",
    owner = Owner("", "", "", "", "")
)
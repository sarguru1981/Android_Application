package com.poc.presentation

import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import com.poc.domain.model.post.Posts


fun getDummyPosts(): Posts {
    return Posts(
        listOf(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b8667d0d8992e610d3f",
                image = "https://img.dummyapi.io/photo-1515376721779-7db6951da88d.jpg",
                likes = 16,
                tags = emptyList(),
                text = "@adventure.yuki frozen grass short-coated black dog sitting on snow",
                publishDate = "2020-05-24T05:44:55.297Z",
                owner = Owner("", "", "", "", "")
            )
        ), 873, 0, 20
    )
}

fun getDummyPostList() = listOf(
    Post(
        id = "60d21b4667d0d8992e610c85",
        image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
        likes = 43,
        tags = emptyList(),
        text = "adult Labrador retriever",
        publishDate = "2020-05-24T14:53:17.598Z",
        owner = Owner("", "", "", "", "")
    ),
    Post(
        id = "60d21b4967d0d8992e610c90",
        image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
        likes = 31,
        tags = emptyList(),
        text = "ice caves in the wild landscape photo of ice near gray cliff",
        publishDate = "2020-05-24T07:44:17.738Z",
        owner = Owner("", "", "", "", "")
    ),
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

fun getDummyPost() = Post(
    id = "60d21b4667d0d8992e610c85",
    image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
    likes = 43,
    tags = emptyList(),
    text = "adult Labrador retriever",
    publishDate = "2020-05-24T14:53:17.598Z",
    owner = Owner("", "", "", "", "")
)
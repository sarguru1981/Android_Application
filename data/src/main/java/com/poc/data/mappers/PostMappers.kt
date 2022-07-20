package com.poc.data.mappers

import com.poc.data.network.model.OwnerDTO
import com.poc.data.network.model.PostDTO
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post

fun List<PostDTO>.toDomain(): List<Post> {
    return map {
        Post(
            id = it.id ?: "",
            image = it.image ?: "",
            likes = it.likes ?: 0,
            owner = it.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = it.publishDate ?: "",
            tags = it.tags ?: emptyList(),
            text = it.text ?: ""

        )
    }
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        firstName = firstName ?: "",
        id = id ?: "",
        lastName = lastName ?: "",
        picture = picture ?: "",
        title = title ?: ""
    )
}

fun PostDTO.toDomain(): Post {
    return Post(
        id = id ?: "",
        image = image ?: "",
        likes = likes ?: 0,
        owner = owner?.toDomain() ?: Owner("", "", "", "", ""),
        publishDate = publishDate ?: "",
        tags = tags ?: emptyList(),
        text = text ?: ""
    )
}

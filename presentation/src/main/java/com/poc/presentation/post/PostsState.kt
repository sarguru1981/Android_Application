package com.poc.presentation.post

import com.poc.domain.model.post.Post


sealed class PostsState {
    object StartState : PostsState()
    object LoadingState : PostsState()
    data class Success(val posts: List<Post>?) : PostsState()
    data class Error(val message: String) : PostsState()
}
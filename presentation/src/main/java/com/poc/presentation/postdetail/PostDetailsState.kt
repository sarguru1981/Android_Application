package com.poc.presentation.postdetail

import com.poc.domain.model.post.Post


sealed class PostDetailState {
    object StartState : PostDetailState()
    object LoadingState : PostDetailState()
    data class Success( val data: Post?) : PostDetailState()
    data class Error(val message: String) : PostDetailState()
}

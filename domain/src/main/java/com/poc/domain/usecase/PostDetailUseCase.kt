package com.poc.domain.usecase

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostListRepository
import com.poc.domain.repository.PostDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostDetailUseCase @Inject constructor(private val postDetailRepository: PostDetailRepository) {

    suspend operator fun invoke(id: String): Flow<Output<Post>> {
        return postDetailRepository.getPostDetails(id)
    }
}

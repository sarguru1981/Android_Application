package com.poc.domain.usecase

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.repository.PostListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostListUseCase @Inject constructor(private val postListRepository: PostListRepository) {

    suspend operator fun invoke(): Flow<Output<List<Post>>> {
        return postListRepository.getPosts()
    }
}

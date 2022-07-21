package com.poc.domain.usecase

import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(private val getPostListRepository: GetPostListRepository) {

    suspend operator fun invoke(): Flow<Output<List<Post>>> {
        return getPostListRepository.getPosts()
    }
}

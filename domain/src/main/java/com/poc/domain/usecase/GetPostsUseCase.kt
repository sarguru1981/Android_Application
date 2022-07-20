package com.poc.domain.usecase

import com.poc.common.Resource
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val getPostsRepository: GetPostsRepository) {

    suspend operator fun invoke():  Flow<List<Post>> {
        return getPostsRepository.getPosts()
    }
}
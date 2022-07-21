package com.poc.domain.usecase

import com.poc.common.Resource
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val getPostsRepository: GetPostsRepository) {

    operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = getPostsRepository.getPosts()
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}
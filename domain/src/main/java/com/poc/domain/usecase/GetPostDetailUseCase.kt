package com.poc.domain.usecase

import com.poc.common.Resource
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val getPostDetailRepository: GetPostDetailRepository) {
    operator fun invoke(id: String): Flow<Resource<Post>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = getPostDetailRepository.getPostDetails(id)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}
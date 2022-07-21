package com.poc.domain.di

import com.poc.domain.repository.GetPostsRepository
import com.poc.domain.repository.PostDetailRepository
import com.poc.domain.usecase.GetPostsUseCase
import com.poc.domain.usecase.PostDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetPostsUseCase(getPostsRepository: GetPostsRepository): GetPostsUseCase {
        return GetPostsUseCase(getPostsRepository)
    }

    @Provides
    fun providePostDetailUseCase(postDetailRepository: PostDetailRepository): PostDetailUseCase {
        return PostDetailUseCase(postDetailRepository)
    }
}
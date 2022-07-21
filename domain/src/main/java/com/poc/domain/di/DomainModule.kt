package com.poc.domain.di

import com.poc.domain.repository.PostListRepository
import com.poc.domain.repository.PostDetailRepository
import com.poc.domain.usecase.PostListUseCase
import com.poc.domain.usecase.PostDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetPostsUseCase(getPostsRepository: PostListRepository): PostListUseCase {
        return PostListUseCase(getPostsRepository)
    }

    @Provides
    fun providePostDetailUseCase(postDetailRepository: PostDetailRepository): PostDetailUseCase {
        return PostDetailUseCase(postDetailRepository)
    }
}
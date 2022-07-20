package com.poc.domain.di

import com.poc.domain.repository.GetPostsRepository
import com.poc.domain.usecase.GetPostsUseCase
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
}
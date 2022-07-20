package com.poc.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.domain.getDummyPosts
import com.poc.domain.repository.GetPostsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPostsRepository: GetPostsRepository
    private lateinit var getPostsUseCase: GetPostsUseCase

    @Before
    fun setUp() {
        getPostsUseCase = GetPostsUseCase(getPostsRepository)
    }

    @Test
    fun `Given Posts When UseCase fetchPosts returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(getDummyPosts())
        Mockito.`when`(getPostsRepository.getPosts()).thenReturn(inputFlow)
        //WHEN
        val output = getPostsUseCase.invoke().toList()
        //THEN
        assert(output[0].size == 1)
    }

}
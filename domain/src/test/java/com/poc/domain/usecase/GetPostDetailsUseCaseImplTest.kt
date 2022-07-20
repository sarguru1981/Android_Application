package com.poc.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.domain.getDummyPost
import com.poc.domain.repository.GetPostDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
class GetPostDetailsUseCaseImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPostDetailRepository: GetPostDetailRepository
    private lateinit var getPostDetailUseCase: GetPostDetailUseCase

    val pathID = "60d21b4667d0d8992e610c85"

    @Before
    fun setUp() {
        getPostDetailUseCase = GetPostDetailUseCase(getPostDetailRepository)
    }

    @Test
    fun `Given PostDetail When UseCase fetchPostDetail returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = getDummyPost()
        Mockito.`when`(getPostDetailRepository.getPostDetails(pathID)).thenReturn(inputFlow)
        //WHEN
        val output = getPostDetailUseCase.invoke(pathID)
        //THEN
        output.collect {
            assert(it.data?.id == "60d21b4667d0d8992e610c85")
        }
    }
}
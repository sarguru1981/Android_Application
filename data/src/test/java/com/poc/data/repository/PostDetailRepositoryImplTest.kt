package com.poc.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.data.getDummyPost
import com.poc.data.network.repository.postdetail.PostDetailRepositoryImpl
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.domain.base.Output
import com.poc.domain.repository.PostDetailRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostDetailRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var postRemoteDataSource: PostDetailRemoteDatasource

    @Mock
    lateinit var retrofit: Retrofit

    private lateinit var postDetailRepository: PostDetailRepository

    private val pathId = "60d21b4667d0d8992e610c85"

    @Before
    fun setUp() {
        postDetailRepository = PostDetailRepositoryImpl(
            postRemoteDataSource,
            retrofit
        )
    }

    @Test
    fun `Given PostDetail When fetchPostDetail returns Success`() = runBlocking {
        //GIVEN
        val givenPosts = getDummyPost()
        val givenPostsOutput = Output.success(givenPosts)
        val inputFlow = listOf(Output.loading(), Output.success(givenPostsOutput))
        Mockito.`when`(postRemoteDataSource.getPostDetails(pathId)).thenReturn(givenPostsOutput)

        //WHEN
        val outputFlow = postDetailRepository.getPostDetails(pathId)

        //THEN
        outputFlow.collect {
            when (it.status) {
                Output.Status.SUCCESS ->
                    assert(it.data?.id == pathId)
            }
        }
    }
}
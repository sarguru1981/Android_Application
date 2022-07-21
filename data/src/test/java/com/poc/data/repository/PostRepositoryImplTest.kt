package com.poc.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.data.getDummyPostList
import com.poc.data.network.repository.post.PostListRepositoryImpl
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.domain.base.Output
import com.poc.domain.repository.PostListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var postRemoteDataSource: PostRemoteDatasource

    @Mock
    lateinit var postLocalDataSource: PostLocalDataSource

    @Mock
    lateinit var postCacheDataSource: PostCacheDataSource

    private lateinit var postsRepository: PostListRepository

    @Before
    fun setUp() {
        postsRepository = PostListRepositoryImpl(
            postRemoteDataSource,
            postLocalDataSource,
            postCacheDataSource,
            retrofit
        )
    }

    @Test
    fun `Given Posts When fetchPosts returns Success`() = runBlocking {
        //GIVEN
        val givenPosts = getDummyPostList()
        val givenPostsOutput = Output.success(givenPosts)
        val inputFlow = listOf(Output.loading(), Output.success(givenPostsOutput))
        Mockito.`when`(postRemoteDataSource.getPosts()).thenReturn(givenPostsOutput)

        //WHEN
        val outputFlow = postsRepository.getPosts().toList()

        //THEN
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
    }
}
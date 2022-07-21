package com.poc.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.poc.data.ApiService
import com.poc.data.getDummyPostList
import com.poc.data.getDummyPosts
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.post.GetPostListRepositoryImpl
import com.poc.data.network.repository.post.GetPostsRepositoryImpl
import com.poc.data.network.repository.post.datasource.GetPostRemoteDatasource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.post.datasourceimpl.GetPostRemoteDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostCacheDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostLocalDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostRemoteDataSourceImpl
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostDatabase
import com.poc.domain.base.Output
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostListRepository
import com.poc.domain.repository.GetPostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
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
    lateinit var postRemoteDataSource: GetPostRemoteDatasource

    @Mock
    lateinit var postLocalDataSource: PostLocalDataSource

    @Mock
    lateinit var postCacheDataSource: PostCacheDataSource

    private lateinit var postsRepository: GetPostListRepository

    @Before
    fun setUp() {
        postsRepository = GetPostListRepositoryImpl(
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
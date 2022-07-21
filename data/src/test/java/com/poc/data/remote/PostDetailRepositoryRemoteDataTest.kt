package com.poc.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.getDummyPost
import com.poc.data.getDummyPosts
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailRemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostDetailRepositoryRemoteDataTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    val pathId = "60d21b4667d0d8992e610c85"

    @Mock
    lateinit var apiService : ApiService

    private lateinit var postDetailRemoteDataSource: PostDetailRemoteDatasource


    @Before
    fun setUp() {
        postDetailRemoteDataSource = PostDetailRemoteDataSourceImpl(apiService, Dispatchers.IO)
    }

    @Test
    fun `Given Post When fetchPost returns Success`() = runBlocking {
        //GIVEN
        val givenPost = getDummyPost()

        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId).body())
            .thenReturn(givenPost)
        //WHEN
        val fetchedPost = postDetailRemoteDataSource.getPostDetails(pathId)


        assert(fetchedPost.id == givenPost.id)
    }

    @Test
    fun `Given Post When fetchPost returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId).body())
            .thenThrow(mockitoException)
        //WHEN
        val fetchedPost = postDetailRemoteDataSource.getPostDetails(pathId)

        //THEN
        assert(fetchedPost.id.toInt() == 0)
    }
}
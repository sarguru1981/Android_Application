package com.poc.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.getDummyPost
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailRemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
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
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostDetailRepositoryRemoteDataTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val pathId = "60d21b4667d0d8992e610c85"

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService : ApiService

    private lateinit var postDetailRemoteDataSource: PostDetailRemoteDatasource


    @Before
    fun setUp() {
        postDetailRemoteDataSource = PostDetailRemoteDataSourceImpl(apiService, retrofit)
    }

    @Test
    fun `Given Post When fetchPost returns Success`() = runBlocking {
        //GIVEN
        val givenPost = getDummyPost()

        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId))
            .thenReturn(Response.success(givenPost))
        //WHEN
        val fetchedPost = postDetailRemoteDataSource.getPostDetails(pathId)


        assert(fetchedPost.data?.id == givenPost.id)
    }

    @Test
    fun `Given Post When fetchPost returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId))
            .thenThrow(mockitoException)
        //WHEN
        val fetchedPost = postDetailRemoteDataSource.getPostDetails(pathId)

        //THEN
        assert(fetchedPost.message == "Unknown Error")
    }

    @Test
    fun `Given Post When fetchPost returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId))
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedPosts =  postDetailRemoteDataSource.getPostDetails(pathId)
        //THEN
        assert(fetchedPosts.message == "Unknown Error")
    }
}
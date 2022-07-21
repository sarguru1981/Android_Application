package com.poc.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.data.ApiService
import com.poc.data.getDummyPosts
import com.poc.data.network.repository.post.datasource.GetPostRemoteDatasource
import com.poc.data.network.repository.post.datasourceimpl.GetPostRemoteDataSourceImpl
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
class PostRepositoryRemoteDataTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var profService: ApiService

    private lateinit var getPostListRemoteDataSource: GetPostRemoteDatasource

    @Before
    fun setUp() {
        getPostListRemoteDataSource = GetPostRemoteDataSourceImpl(profService, retrofit)
    }

    @Test
    fun `Given Posts When fetchPosts returns Success`() = runBlocking {
        //GIVEN
        val givenPosts = getDummyPosts()
        Mockito.`when`(profService.getPosts()).thenReturn(Response.success(givenPosts))
        //WHEN
        val fetchedPosts = getPostListRemoteDataSource.getPosts()

        //THEN
        assert(fetchedPosts.data?.size == givenPosts.data.size)
    }

    @Test
    fun `Given Posts When fetchPosts returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(profService.getPosts()).thenThrow(mockitoException)
        //WHEN
        val fetchedPosts = getPostListRemoteDataSource.getPosts()
        //THEN
        assert(fetchedPosts.message == "Unknown Error")
    }

    @Test
    fun `Given Posts When fetchPosts returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(profService.getPosts())
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedPosts = getPostListRemoteDataSource.getPosts()
        //THEN
        assert(fetchedPosts.message == "Unknown Error")
    }
}
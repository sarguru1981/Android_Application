package com.poc.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.repository.post.datasourceimpl.PostRemoteDataSourceImpl
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostRepositoryRemoteDataTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService : ApiService

    private lateinit var postRemoteDataSource: PostRemoteDatasource


    @Before
    fun setUp() {
        postRemoteDataSource = PostRemoteDataSourceImpl(apiService, Dispatchers.IO)
    }

    @Test
    fun `Given Posts When fetchPosts returns Success`() = runBlocking {
        //GIVEN
        val givenPosts = getDummyPosts()

        Mockito.`when`(apiService.getPosts().data?.toDomain())
            .thenReturn(givenPosts)
        //WHEN
        val fetchedPosts = postRemoteDataSource.getPosts()

        //THEN
        val flatPostList: List<Post> = fetchedPosts.flattenToList()

        assert(flatPostList.size == givenPosts.size)
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()

    @Test
    fun `Given Posts When fetchPosts returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getPosts()).thenThrow(mockitoException)
        //WHEN
        val fetchedPosts = postRemoteDataSource.getPosts()
        //THEN
        val flatPostList: List<Post> = fetchedPosts.flattenToList()

        //THEN
        assert(flatPostList.isEmpty())
    }

    private fun getDummyPosts(): List<Post> {
        return listOf(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b8667d0d8992e610d3f",
                image = "https://img.dummyapi.io/photo-1515376721779-7db6951da88d.jpg",
                likes = 16,
                tags = emptyList(),
                text = "@adventure.yuki frozen grass short-coated black dog sitting on snow",
                publishDate = "2020-05-24T05:44:55.297Z",
                owner = Owner("", "", "", "", "")
            )
        )
    }
}
package com.poc.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.PostDetailRemoteDataSourceImpl
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.postdetail.GetPostDetailRepositoryImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailCacheDataSourceImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailLocalDataSourceImpl
import com.poc.data.room.post.PostDatabase
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPostDetailRepository
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
class PostDetailRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    private lateinit var postDetailRepository: GetPostDetailRepository

    val pathId = "60d21b4667d0d8992e610c85"

    @Before
    fun setUp() {
        val postDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).build()
        val postDAO = postDatabase.getPostDAO()
        val postDetailRemoteDatasource = PostDetailRemoteDataSourceImpl(apiService, Dispatchers.IO)
        val postDetailLocalDataSource = PostDetailLocalDataSourceImpl(postDAO)
        val postDetailCacheDataSource = PostDetailCacheDataSourceImpl()
        postDetailRepository = GetPostDetailRepositoryImpl(
            postDetailRemoteDatasource,
            postDetailLocalDataSource,
            postDetailCacheDataSource
        )
    }

    @Test
    fun `Given PostDetail When fetchPostDetail returns Success`() = runBlocking {
        //GIVEN
        val givenPostDetail = getDummyPost()

        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId).body()?.toDomain())
            .thenReturn(givenPostDetail)
        //WHEN
        val fetchedPostDetail = postDetailRepository.getPostDetails(pathId)

        //THEN
        assert(fetchedPostDetail.id == givenPostDetail.id)
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()

    @Test
    fun `Given PostDetail When fetchPostDetail returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getPostDetails(Constant.APP_ID, pathId))
            .thenThrow(mockitoException)
        //WHEN
        val fetchedPostDetail = postDetailRepository.getPostDetails(pathId)

        //THEN
        assert(fetchedPostDetail.id.toInt() == 0)
    }

    private fun getDummyPost(): Post {
        return Post(
            id = "60d21b4667d0d8992e610c85",
            image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
            likes = 43,
            tags = emptyList(),
            text = "adult Labrador retriever",
            publishDate = "2020-05-24T14:53:17.598Z",
            owner = Owner("", "", "", "", "")
        )
    }
}
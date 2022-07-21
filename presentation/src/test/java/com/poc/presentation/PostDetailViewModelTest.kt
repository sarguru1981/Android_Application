package com.poc.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailRemoteDataSourceImpl
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.network.model.OwnerDTO
import com.poc.data.network.model.PostDTO
import com.poc.data.network.repository.postdetail.GetPostDetailRepositoryImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailCacheDataSourceImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailLocalDataSourceImpl
import com.poc.data.network.utils.SafeApiRequest
import com.poc.data.room.post.PostDatabase
import com.poc.domain.repository.GetPostDetailRepository
import com.poc.domain.usecase.GetPostDetailUseCase
import com.poc.presentation.postdetail.PostDetailState
import com.poc.presentation.postdetail.PostDetailsViewModel
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PostDetailViewModelTest : SafeApiRequest() {

    private lateinit var postDetailsViewModel: PostDetailsViewModel
    private lateinit var postDetailUseCase: GetPostDetailUseCase
    private var apiService = mock<ApiService>()
    private lateinit var postDetailRepository: GetPostDetailRepository

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

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

    /*@Test
    fun `Loading state works`() = runBlocking {
        whenever(
            apiService.getPostDetails(
                appId = Constant.APP_ID,
                id = "60d21b4667d0d8992e610c85"
            ).body()
        ).doSuspendableAnswer {
            withContext(Dispatchers.IO) { delay(5000) }
            PostDTO(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = OwnerDTO("", "", "", "", "")
            )
        }
        postDetailUseCase =
            GetPostDetailUseCase(postDetailRepository)

        postDetailsViewModel =
            PostDetailsViewModel(
                postDetailUseCase,
                SavedStateHandle()
            )
        Assert.assertNotEquals(PostDetailState.StartState, postDetailsViewModel.details.value)
    }

    @Test
    fun `Start state works`() = runBlocking {
        whenever( apiService.getPostDetails(
            appId = Constant.APP_ID,
            id = "60d21b4667d0d8992e610c85"
        ).body()).thenReturn(PostDTO(
            id = "60d21b4667d0d8992e610c85",
            image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
            likes = 43,
            tags = emptyList(),
            text = "adult Labrador retriever",
            publishDate = "2020-05-24T14:53:17.598Z",
            owner = OwnerDTO("", "", "", "", "")
        ))

        postDetailUseCase =
            GetPostDetailUseCase(postDetailRepository)

        postDetailsViewModel =
            PostDetailsViewModel(
                postDetailUseCase,
                SavedStateHandle()
            )
        Assert.assertEquals(PostDetailState.StartState, postDetailsViewModel.details.value)
    }*/
}


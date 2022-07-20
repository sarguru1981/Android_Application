package com.poc.presentation

import androidx.lifecycle.SavedStateHandle
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.network.model.OwnerDTO
import com.poc.data.network.model.PostDTO
import com.poc.data.network.repository.postdetail.GetPostDetailRepositoryImpl
import com.poc.data.network.repository.user.GetUsersRepositoryImpl
import com.poc.data.network.utils.SafeApiRequest
import com.poc.domain.usecase.GetPostDetailUseCase
import com.poc.presentation.postdetail.PostDetailState
import com.poc.presentation.postdetail.PostDetailsViewModel
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PostDetailViewModelTest : SafeApiRequest() {

    private lateinit var postDetailsViewModel: PostDetailsViewModel
    private lateinit var postDetailUseCase: GetPostDetailUseCase
    private var apiService = mock<ApiService>()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //postDetailUseCase = Mockito.mock(GetPostDetailUseCase::class.java)
        /*Mockito.`when`(postDetailUseCase.invoke("60d21b4667d0d8992e610c85"))
            .thenReturn(emptyList())*/
    }

    @Test
    fun `Loading state works`() = runBlocking {
        whenever( apiService.getPostDetails(
            appId = Constant.APP_ID,
            id = "60d21b4667d0d8992e610c85"
        )?.body()).doSuspendableAnswer {
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
            GetPostDetailUseCase(GetPostDetailRepositoryImpl(apiService, Dispatchers.IO))

        postDetailsViewModel =
            PostDetailsViewModel(
                postDetailUseCase,
                GetUsersUseCase(GetUsersRepositoryImpl(apiService, Dispatchers.IO)),
                SavedStateHandle()
            )
        Assert.assertEquals(PostDetailState.StartState, postDetailsViewModel.details.value)
    }

    @Test
    fun `Start state works`() = runBlocking {
        whenever( apiService.getPostDetails(
            appId = Constant.APP_ID,
            id = "60d21b4667d0d8992e610c85"
        )?.body()).thenReturn(PostDTO(
            id = "60d21b4667d0d8992e610c85",
            image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
            likes = 43,
            tags = emptyList(),
            text = "adult Labrador retriever",
            publishDate = "2020-05-24T14:53:17.598Z",
            owner = OwnerDTO("", "", "", "", "")
        ))
        postDetailUseCase =
            GetPostDetailUseCase(GetPostDetailRepositoryImpl(apiService, Dispatchers.IO))

        postDetailsViewModel =
            PostDetailsViewModel(
                postDetailUseCase,
                GetUsersUseCase(GetUsersRepositoryImpl(apiService, Dispatchers.IO)),
                SavedStateHandle()
            )
        Assert.assertEquals(PostDetailState.StartState, postDetailsViewModel.details.value)
    }
}


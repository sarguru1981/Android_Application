package com.poc.presentation

import android.content.Context
import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.network.repository.post.GetPagerPostRepoImpl
import com.poc.data.network.repository.post.GetPostsRepositoryImpl
import com.poc.data.network.utils.SafeApiRequest
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostDatabase
import com.poc.domain.usecase.GetPostsUseCase
import com.poc.presentation.post.PostListViewModel
import com.poc.presentation.post.PostsState
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PostListViewModelTest : SafeApiRequest() {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var apiService = mock<ApiService>()

    private lateinit var postListViewModel: PostListViewModel
    private lateinit var getPostsUseCase: GetPostsUseCase
    private lateinit var postDAO: PostDAO

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        postDAO = PostDatabase.getInstance(Mockito.mock(Context::class.java)).getPostDAO()
    }

    @Test
    fun `Loading state works`() = runBlocking {
        whenever((apiService.getPostList()?.body()?.data?.toDomain())).doSuspendableAnswer {
            withContext(Dispatchers.IO) { delay(5000) }
            emptyList()
        }
        getPostsUseCase = GetPostsUseCase(GetPostsRepositoryImpl(apiService, Dispatchers.IO))
        postListViewModel =
            PostListViewModel(getPostsUseCase, GetPagerPostRepoImpl(apiService), postDAO)
        Assert.assertEquals(PostsState.LoadingState, postListViewModel.postState.value)
    }

    @Test
    fun `Success state works`() = runBlocking {
        whenever(apiService.getPostList()?.body()?.data?.toDomain()).thenReturn(emptyList())
        getPostsUseCase = GetPostsUseCase(GetPostsRepositoryImpl(apiService, Dispatchers.IO))
        postListViewModel =
            PostListViewModel(getPostsUseCase, GetPagerPostRepoImpl(apiService), postDAO)
        Assert.assertNotEquals(PostsState.Success(emptyList()), postListViewModel.postState.value)
    }

    @Test
    fun `Failure state works`() = runBlocking {
        whenever(apiService.getPostList()?.body()?.data?.toDomain()).thenReturn(emptyList())
        getPostsUseCase = GetPostsUseCase(GetPostsRepositoryImpl(apiService, Dispatchers.IO))
        postListViewModel =
            PostListViewModel(getPostsUseCase, GetPagerPostRepoImpl(apiService), postDAO)
        Assert.assertNotEquals(
            PostsState.Error(message = "Method i in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details."),
            postListViewModel.postState.value
        )
    }
}
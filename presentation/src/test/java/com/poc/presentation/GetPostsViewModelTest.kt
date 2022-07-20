package com.poc.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.poc.data.ApiService
import com.poc.data.mappers.toDomain
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostDatabase
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import com.poc.domain.usecase.GetPostsUseCase
import com.poc.presentation.post.PostListViewModel
import com.poc.presentation.post.PostsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetPostsViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var apiService = mock<ApiService>()
    private lateinit var postListViewModel: PostListViewModel
    private lateinit var getPostsUseCase: GetPostsUseCase
    private lateinit var postDAO: PostDAO

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postDAO = PostDatabase.getInstance(Mockito.mock(Context::class.java)).getPostDAO()
        val fakeGetPostRepository = FakeGetPostRepository(apiService)
        getPostsUseCase = GetPostsUseCase(fakeGetPostRepository)
    }

    @Test
    fun getPost_returnsCurrentList() {
        val posts = mutableListOf<Post>()
        posts.add(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            )
        )
        posts.add(
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            )
        )
        posts.add(
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

        postListViewModel = PostListViewModel(getPostsUseCase)

        postListViewModel.viewModelScope.launch {
            whenever(apiService.getPosts().data?.toDomain()).thenReturn(posts)
        }

        Assert.assertNotEquals(PostsState.Success(emptyList()), postListViewModel.postState.value)

    }
}
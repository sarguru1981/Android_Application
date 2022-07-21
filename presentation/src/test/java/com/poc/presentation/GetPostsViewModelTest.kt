package com.poc.presentation

import com.poc.domain.base.Output
import com.poc.domain.usecase.GetPostListUseCase
import com.poc.presentation.post.PostListViewModel
import com.poc.presentation.post.PostsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var postListUseCase: GetPostListUseCase
    private lateinit var postListViewModel: PostListViewModel

    @Before
    fun setUp() {
        postListViewModel = PostListViewModel(postListUseCase)
    }

    @Test
    fun `Given Posts when getPosts should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyPostList()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(postListUseCase).invoke()
        postListViewModel.getPosts()

        //THEN
        when (val postState = postListViewModel.postState.value) {
            is PostsState.Success -> {

                assert(3 == postState.posts?.size)
            }
        }
    }

}
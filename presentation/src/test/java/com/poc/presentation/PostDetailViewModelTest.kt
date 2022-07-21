package com.poc.presentation

import androidx.lifecycle.SavedStateHandle
import com.poc.domain.base.Output
import com.poc.domain.usecase.PostDetailUseCase
import com.poc.presentation.postdetail.PostDetailState
import com.poc.presentation.postdetail.PostDetailsViewModel
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
class PostDetailViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var postDetailUseCase: PostDetailUseCase

    private lateinit var postDetailsViewModel: PostDetailsViewModel


    private val pathId = "60d21b4667d0d8992e610c85"

    @Before
    fun setUp() {
        postDetailsViewModel = PostDetailsViewModel(postDetailUseCase, SavedStateHandle())
    }

    @Test
    fun `Given Posts when getPosts should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowQuestions = flowOf(Output.success(getDummyPost()))

        //WHEN
        Mockito.doReturn(flowQuestions).`when`(postDetailUseCase).invoke(pathId)
        postDetailsViewModel.getPostDetails(pathId)

        //THEN
        when (val postState = postDetailsViewModel.details.value) {
            is PostDetailState.Success -> {

                assert(pathId == postState.data?.id)
            }
        }
    }
}


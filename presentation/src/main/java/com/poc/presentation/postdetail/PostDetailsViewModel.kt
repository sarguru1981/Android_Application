package com.poc.presentation.postdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.domain.base.Output
import com.poc.domain.usecase.PostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postDetailUseCase: PostDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _details = MutableStateFlow<PostDetailState>(PostDetailState.StartState)
    // Exposing immutable types to other classes. Changes to the mutable type is centralized in one class
    val details: StateFlow<PostDetailState> = _details

    init {
        savedStateHandle.getLiveData<String>("blogId").value?.let {
             getPostDetails(it)
        }
    }


    fun getPostDetails(id: String) {
        viewModelScope.launch {
            postDetailUseCase.invoke(id).collect {
                when (it.status) {
                    Output.Status.SUCCESS -> _details.value = PostDetailState.Success(it.data)
                    Output.Status.ERROR -> _details.value = PostDetailState.Error(it.message.toString())
                    Output.Status.LOADING -> _details.value = PostDetailState.LoadingState
                }
            }
        }
    }
}
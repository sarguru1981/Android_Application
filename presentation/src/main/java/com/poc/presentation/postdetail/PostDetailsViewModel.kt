package com.poc.presentation.postdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.common.Resource
import com.poc.domain.usecase.GetPostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
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
        getPostDetailUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> _details.value = PostDetailState.LoadingState
                is Resource.Success -> _details.value = PostDetailState.Success(it.data)
                is Resource.Error -> _details.value = PostDetailState.Error(it.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}
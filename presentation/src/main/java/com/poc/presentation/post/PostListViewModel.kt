package com.poc.presentation.post


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.common.Resource
import com.poc.domain.base.Output
import com.poc.domain.model.post.Post
import com.poc.domain.usecase.GetPostListUseCase
import com.poc.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostListUseCase,
) : ViewModel() {


    // Backing property to avoid state updates from other classes
    private val _postState = MutableStateFlow<PostsState>(PostsState.StartState)

    // The UI collects from this StateFlow to get its state updates
    val postState: StateFlow<PostsState> = _postState


    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase.invoke().collect {
                when (it.status) {
                    Output.Status.SUCCESS -> _postState.value = PostsState.Success(it.data)
                    Output.Status.ERROR -> _postState.value = PostsState.Error(it.message.toString())
                    Output.Status.LOADING -> _postState.value = PostsState.LoadingState
                }
            }
        }
    }

    /*private fun getPosts() {
        getPostsUseCase().onEach {
            when (it) {
                is Resource.Loading -> _postState.value = PostsState.LoadingState
                is Resource.Success -> _postState.value = PostsState.Success(it.data)
                is Resource.Error -> _postState.value = PostsState.Error(it.message.toString())
            }
        }.launchIn(viewModelScope)
    }*/
}





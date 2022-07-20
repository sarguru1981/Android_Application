package com.poc.presentation.post


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.poc.data.paging.PostRemoteMediator
import com.poc.data.room.post.PostDAO
import com.poc.domain.repository.GetPagerPostRepo
import com.poc.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    getPagerPostRepo: GetPagerPostRepo,
    private val postDAO: PostDAO,
) : ViewModel() {


    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        remoteMediator = PostRemoteMediator(
            getPagerPostRepo = getPagerPostRepo,
            postDAO = postDAO
        )
    ) {
        postDAO.getAllPostItems()
    }.flow.cachedIn(viewModelScope)




    // Backing property to avoid state updates from other classes
    private val _postState = MutableStateFlow<PostsState>(PostsState.StartState)
    // The UI collects from this StateFlow to get its state updates
    val postState: StateFlow<PostsState> = _postState


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            _postState.value = PostsState.LoadingState
            // Improperly handled exceptions thrown in coroutines can make your app crash.
            // Hence caught them in the body of any coroutines created with viewModelScope
            try {
                getPostsUseCase()
                    //The third party library can throw unexpected exceptions. Hence caught here
                    .catch { exception -> Log.i("PostListViewModel", exception.toString()) }
                    // Update View with the latest posts
                    // Writes to the value property of MutableStateFlow,
                    // adding a new element to the flow and updating all
                    // of its collectors
                    .collect {
                        postList -> _postState.value = PostsState.Success(postList)
                    }
            } catch (error: Throwable) {
                _postState.value = PostsState.Error(error.message.toString())
            }
        }
    }
}





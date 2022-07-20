package com.poc.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.poc.common.Resource
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostKey
import com.poc.domain.model.post.Post
import com.poc.domain.repository.GetPagerPostRepo
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val getPagerPostRepo: GetPagerPostRepo,
    private val postDAO: PostDAO
) : RemoteMediator<Int, Post>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Post>): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.next ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestKey(state)
                    remoteKeys?.next?.minus(1) ?: initialPage
                }
            }
            val response =
                getPagerPostRepo.getPagerPosts(page = page, limit = state.config.pageSize)
            val endOfPagination = response.data?.size!! < state.config.pageSize
            when (response) {
                is Resource.Success -> {
                    val body = response.data
                    if (loadType == LoadType.REFRESH) {
                        postDAO.deleteAllItems()
                        postDAO.deleteAllItems()
                    }
                    val prev = if (page == initialPage) initialPage else page - 1
                    val next = if (endOfPagination) null else page + 1
                    val list = body?.map {
                        PostKey(id = it.id, prev, next)
                    }
                    list?.let { postDAO.insertAllPostKeys(list) }
                    body?.let { postDAO.insertAllPosts(body) }
                }
                is Resource.Error -> MediatorResult.Error(Exception())
            }
            if (response is Resource.Success) {
                if (endOfPagination) MediatorResult.Success(true)
                else MediatorResult.Success(false)
            } else MediatorResult.Success(true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    suspend fun getLastKey(state: PagingState<Int, Post>): PostKey? {
        return state.lastItemOrNull()?.let {
            postDAO.getAllKeys(it.id)
        }
    }

    suspend fun getClosestKey(state: PagingState<Int, Post>): PostKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                postDAO.getAllKeys(it.id)
            }
        }
    }
}


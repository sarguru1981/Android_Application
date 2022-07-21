package com.poc.data.room.post

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.domain.model.post.Post

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(list: List<Post>)

    @Query("SELECT * FROM Post")
    suspend fun getPosts():List<Post>

    @Query("SELECT * FROM Post")
    fun getAllPostItems(): PagingSource<Int, Post>

    @Query("DELETE FROM Post")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostItem(post: Post)

    @Query("SELECT * FROM Post WHERE id=:id")
    suspend fun getPostItem(id: String): Post

    @Query("DELETE FROM Post WHERE id=:id")
    suspend fun deletePostItem(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostKeys(l: List<PostKey>)

    @Query("DELETE FROM PostKey")
    suspend fun deleteAllPostKey()

    @Query("SELECT * FROM PostKey WHERE id=:id")
    suspend fun getAllKeys(id: String): PostKey
}
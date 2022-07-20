package com.poc.data.room.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.poc.domain.model.post.Owner
import com.poc.domain.model.post.Post
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: PostDAO
    private lateinit var postDatabase: PostDatabase

    @Before
    fun setUp() {
        postDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).build()
        dao = postDatabase.getPostDAO()
    }

    @After
    fun tearDown() {
        postDatabase.close()
    }

    @Test
    fun insertAllPostsTest() = runBlocking{
        val posts = listOf(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            ),
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
        dao.insertAllPosts(posts)

        val allPosts = dao.getPosts()
        Truth.assertThat(allPosts).isEqualTo(posts)
    }

    @Test
    fun deletePostsTest() = runBlocking {
        val posts = listOf(
            Post(
                id = "60d21b4667d0d8992e610c85",
                image = "https://img.dummyapi.io/photo-1564694202779-bc908c327862.jpg",
                likes = 43,
                tags = emptyList(),
                text = "adult Labrador retriever",
                publishDate = "2020-05-24T14:53:17.598Z",
                owner = Owner("", "", "", "", "")
            ),
            Post(
                id = "60d21b4967d0d8992e610c90",
                image = "https://img.dummyapi.io/photo-1510414696678-2415ad8474aa.jpg",
                likes = 31,
                tags = emptyList(),
                text = "ice caves in the wild landscape photo of ice near gray cliff",
                publishDate = "2020-05-24T07:44:17.738Z",
                owner = Owner("", "", "", "", "")
            ),
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
        dao.insertAllPosts(posts)
        dao.deleteAllItems()
        val postResult = dao.getPosts()
        Truth.assertThat(postResult).isEmpty()
    }
}
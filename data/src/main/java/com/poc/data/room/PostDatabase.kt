package com.poc.data.room.post

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poc.domain.model.post.Post

@Database(entities = [Post::class, PostKey::class], version = 1, exportSchema = false)
@TypeConverters(PostTypeConvertor::class, ListOfStringToStringTypeConvertor::class)
abstract class PostDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): PostDatabase {
            return Room.databaseBuilder(context, PostDatabase::class.java, "post").build()
        }
    }

    abstract fun getPostDAO() : PostDAO
}
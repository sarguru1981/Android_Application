package com.poc.data.room.post

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.poc.domain.model.post.Owner

class PostTypeConvertor {
    @TypeConverter
    fun ownerToString(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(str: String): Owner {
        return Gson().fromJson(str, Owner::class.java)
    }
}


class ListOfStringToStringTypeConvertor {
    @TypeConverter
    fun listOfStringToString(str: List<String>): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun strToListString(str: String): List<String> {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }

}
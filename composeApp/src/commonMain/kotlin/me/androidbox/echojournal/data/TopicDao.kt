package me.androidbox.echojournal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopicDao {

    @Query("SELECT * FROM topic")
    fun getAll(): List<Topic>

    @Query("SELECT * FROM topic WHERE title LIKE :title || '%'")
    fun getTopicWithPrefix(title: String): List<Topic>

    @Insert
    fun insertAll(vararg topic: Topic)

    @Delete
    fun delete(topic: Topic)
}
package me.androidbox.echojournal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    suspend fun getAll(): List<Journal>

    @Insert
    suspend fun insertAll(vararg journal: Journal)

    @Delete
    fun delete(journal: Journal)
}
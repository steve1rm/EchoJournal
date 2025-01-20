package me.androidbox.echojournal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal")
data class Journal(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "audioFilePath") val audioFilePath: String?,
    @ColumnInfo(name = "topics") val topics: List<String>?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "emotion") val emotion: String?,
    @ColumnInfo(name = "createdAt") val createdAt: Long
)

package me.androidbox.echojournal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal")
data class Journal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "audioFilePath") val audioFilePath: String?,
    @ColumnInfo(name = "topics") val topics: List<String>?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "emotion") val emotion: String?,
    @ColumnInfo(name = "createdAt") val createdAt: Long,
    @ColumnInfo(name = "audioDuration") val audioDuration: Long
)

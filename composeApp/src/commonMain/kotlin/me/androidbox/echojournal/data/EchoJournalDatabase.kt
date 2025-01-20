package me.androidbox.echojournal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Journal::class, Topic::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class EchoJournalDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun journalDao(): JournalDao
}
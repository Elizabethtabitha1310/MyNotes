package com.example.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.dao.NoteDao
import com.example.mynotes.entity.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao
}
package com.example.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.dao.NoteDao
import org.openjdk.tools.javac.util.JCDiagnostic

@Database(entities = arrayOf(JCDiagnostic.Note::class), version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao
}
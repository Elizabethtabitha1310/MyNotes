package com.example.noteapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mynotes.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query(value = "select * from notes ORDER BY date Desc")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    suspend  fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
suspend fun deleteNote(note: Note)
}
package com.example.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.Model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteTable LIMIT 1")
    fun getNotepad(): LiveData<Note?>

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}
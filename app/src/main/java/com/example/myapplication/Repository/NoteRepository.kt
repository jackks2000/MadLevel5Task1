package com.example.myapplication.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.DAO.NoteDao
import com.example.myapplication.Database.NoteRoomDatabase
import com.example.myapplication.Model.Note

public class NoteRepository(context: Context) {

    private var noteDao: NoteDao

    init {
        val reminderRoomDatabase = NoteRoomDatabase.getDatabase(context)
        noteDao = reminderRoomDatabase!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return noteDao.getNotepad()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }


    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}

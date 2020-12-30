package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Model.Note
import com.example.myapplication.Repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertNote(note: Note){
        mainScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun updateNote(title: String, text: String) {

        //if there is an existing note, take that id to update it instead of adding a new one
        val newNote = Note(
            id = note.value?.id,
            title = title,
            lastUpdate = Date(),
            text = text
        )

        if(isNoteValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNote(newNote)
                }
                success.value = true
            }
        }
    }

    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }



    fun deleteNote(note: Note){
        mainScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}
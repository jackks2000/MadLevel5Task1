package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Model.Note
import com.example.myapplication.ViewModel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.util.*

class AddNoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_note_btn.setOnClickListener{
            onAddNote()
        }
        observeNote()
    }

    private fun observeNote(){
        viewModel.note.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let{
                etNoteTitle.setText(it.title)
                etNoteText.setText(it.text)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, androidx.lifecycle.Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, androidx.lifecycle.Observer { success ->
            //"pop" the backstack, this means we destroy this    fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        })
    }


    private fun onAddNote(){
        viewModel.updateNote(etNoteTitle.text.toString(), etNoteText.text.toString())
    }
}
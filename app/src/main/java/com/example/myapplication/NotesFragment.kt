package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Note
import com.example.myapplication.ViewModel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotesFragment : Fragment() {


    private val viewModel: NoteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddNoteResult()
    }

    private fun initViews() {

//        createItemTouchHelper().attachToRecyclerView(notes_rv)

    }

    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                println("title ${it}")
                tvTitle.setText(it.title)
                tvLastUpdated.text = Date().toString()
                tvText.text = it.text
            }
        })
    }




//    private fun createItemTouchHelper(): ItemTouchHelper {
//
//        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
//        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
//        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//
//            // Enables or Disables the ability to move items up and down.
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            // Callback triggered when a user swiped an item.
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//
//                val noteToDelete = notes[position]
//
//                viewModel.deleteNote(noteToDelete)
//            }
//        }
//
//
//
//        return ItemTouchHelper(callback)
//    }

}
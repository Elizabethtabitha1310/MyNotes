package com.example.mynotes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mynotes.R
import com.example.mynotes.adapter.NoteAdapter
import com.example.mynotes.databinding.FragmentNoteBinding
import com.example.mynotes.viewModel.NoteViewModel
import com.example.noteapp.data.entity.Note
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteFragment: Fragment(R.layout.fragment_note),NoteAdapter.OnNoteClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<NoteViewModel>()
        val binding = FragmentNoteBinding.bind(requireView())

        binding.apply {
            recyclerViewNote.layoutManager = GridLayoutManager(context,2)
          recyclerViewNote.setHasFixedSize(true)
            addBtn.setOnClickListener{
                val action =NoteFragmentDirections.actionNoteFragment2ToAddEditNoteFragment2(null)
            findNavController().navigate(action)
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.notes.collect{ notes->
                    val adapter = NoteAdapter(notes,this@NoteFragment)
               recyclerViewNote.adapter = adapter
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.notesEvent.collect{ event->
                    if(event is NoteViewModel.NotesEvent.ShowUndoSnackBar){
                        Snackbar.make(requireView(),event.msg,Snackbar.LENGTH_LONG).setAction("UNDO"){
                           viewModel.insertNote(event.note)
                        }.show()
                    }
                }
            }
        }
    }

    override fun onNoteClick(note: Note) {
        val action = NoteFragmentDirections.actionNoteFragment2ToAddEditNoteFragment2(note)
   findNavController().navigate(action)
    }

    override fun onNoteLongClick(note: Note) {
        val viewModel by viewModels<NoteViewModel>()
viewModel.deleteNote(note)
    }
}
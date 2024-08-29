package com.example.mynotes.adapter

import android.view.LayoutInflater
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.entity.Note
import java.text.SimpleDateFormat

class NoteAdapter(private val mNotes:List<Note>,private val listener: OnNoteClickListener):RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
interface OnNoteClickListener{
    fun OnNoteClickListener(note: Note)
    fun OnNoteLongClick(note: Note)
}
    inner class ViewHolder(private val binding:ItemNotesBinding):RecyclerView.ViewHolder(binding.root){
      init {
          binding.apply{
              root.setOnclickListener{
                  val position = adapterPosition
                  if (position != RecyclerView.NO_POSITION){
                      val note = mNotes[position]
                      listener.OnNoteClickListener(note)
                  }
              }
              root.setOnclickListener {
                  val position = adapterPosition
                  if (position != RecyclerView.NO_POSITION) {
                      val note = mNotes[position]
                      listener.OnNoteLongClick(note)
                  }
                  true
              }
          }
      }
        fun bind(note: Note){
     binding.apply{
    titleNote.text = note.title
    contentNote.text = note.content
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    dateNote.text = formatter.format(note.date)
}
   }
}

    override fun onCreateViewHolder(parent: ViewParent,viewType:Int){
        val  binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return ViewHolder(binding)
    }

    override fun onBindingViewHolder(holder: ViewHolder, position: Int){
        with(mNotes[position]){
            holder.bind(this)
        }
    }

    override fun getItemCount():Int{
        return mNotes.size
    }
}
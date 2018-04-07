package kuzhelkodmitry.simplenotes.presentation.notesList.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_of_notes_list.*
import kuzhelkodmitry.simplenotes.R
import kuzhelkodmitry.simplenotes.domain.entities.Note


class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes: List<Note> = emptyList()
    private lateinit var clickListener: ClickListener
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    interface ClickListener {

        fun onEditClick(note: Note, position: Int)

        fun onDeleteClick(note: Note, position: Int)
    }

    internal fun updateAdapter(notes: List<Note>) {
        this.notes = notes
    }

    internal fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    internal fun addNote() {
        notifyItemInserted(itemCount - 1)
    }

    internal fun removeNote(position: Int) {
        notifyItemRemoved(position)
    }

    internal fun updateNote() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = inflater.inflate(R.layout.item_of_notes_list, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }

    override fun getItemCount(): Int = if (notes.isEmpty()) 0 else notes.size

    inner class NotesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindNote(note: Note) {
            noteTitle.text = note.title
            noteDescription.text = note.description
        }

        init {
            editNote.setOnClickListener {
                clickListener.onEditClick(notes[adapterPosition], adapterPosition)
            }

            deleteNote.setOnClickListener {
                clickListener.onDeleteClick(notes[adapterPosition], adapterPosition)
            }
        }
    }
}
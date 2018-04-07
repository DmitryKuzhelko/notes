package kuzhelkodmitry.simplenotes.presentation.notesList.presenter

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.util.Log
import kuzhelkodmitry.simplenotes.ID_IS_EMPTY
import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.domain.interactors.notesList.NotesInteractor
import kuzhelkodmitry.simplenotes.presentation.base.presenter.BasePresenter
import kuzhelkodmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity
import kuzhelkodmitry.simplenotes.presentation.notesList.view.INotesView
import javax.inject.Inject

class NotesPresenter<V : INotesView>
@Inject constructor(private val context: Context, private val interactor: NotesInteractor) : BasePresenter<V>(), INotesPresenter<V>, LifecycleObserver {

    override fun setScreen() {
        interactor.getNotes().subscribe({ notes ->
            when (notes.isEmpty()) {
                true -> mvpView!!.showEmptyScreen()
                false -> {
                    mvpView!!.hideEmptyScreen()
                    mvpView!!.addDataToAdapter(notes)
                }
            }
        }, { Log.i("NotesPresenter", "Some error in setScreen method") })
    }

    override fun addNote() {
        mvpView!!.startDetailActivity(NoteDetailActivity.getNoteDetailIntent(context = context))
    }

    override fun deleteNote(note: Note, position: Int) {
        mvpView!!.deleteNote(position)
        interactor.removeNote(note.id)
    }

    override fun editNote(noteId: String, position: Int) {
        mvpView!!.startDetailActivity(NoteDetailActivity.getNoteDetailIntent(context, noteId, position))
    }

    override fun createOrUpdateNote(noteId: String, title: String, description: String) {
        val note: Note
        when (noteId) {
            ID_IS_EMPTY -> {
                note = Note(title = title, description = description)
                mvpView!!.addNote()
            }
            else -> {
                note = Note(noteId, title, description)
                mvpView!!.updateNote()
            }
        }
        interactor.createOrUpdateNote(note)
    }
}
package kuzhelkodmitry.simplenotes.presentation.notesList.presenter

import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.presentation.base.presenter.IMvpPresenter
import kuzhelkodmitry.simplenotes.presentation.notesList.view.INotesView

interface INotesPresenter<in V : INotesView> : IMvpPresenter<V> {

    fun setScreen()

    fun addNote()

    fun deleteNote(note: Note, position: Int)

    fun editNote(noteId: String, position: Int)

    fun createOrUpdateNote(noteId: String, title: String, description: String)
}
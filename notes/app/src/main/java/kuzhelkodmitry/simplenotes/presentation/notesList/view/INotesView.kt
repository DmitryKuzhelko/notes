package kuzhelkodmitry.simplenotes.presentation.notesList.view

import android.content.Intent

import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.presentation.base.view.IMvpView

interface INotesView : IMvpView {

    fun addDataToAdapter(notes: List<Note>)

    fun startDetailActivity(intent: Intent)

    fun deleteNote(position: Int)

    fun addNote()

    fun updateNote()

    fun hideEmptyScreen()

    fun showEmptyScreen()
}
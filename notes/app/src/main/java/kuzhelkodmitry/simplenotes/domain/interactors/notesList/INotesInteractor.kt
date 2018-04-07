package kuzhelkodmitry.simplenotes.domain.interactors.notesList

import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

interface INotesInteractor {

    fun getNotes(): Single<List<Note>>

    fun removeNote(noteId: String)

    fun createOrUpdateNote(note: Note)
}
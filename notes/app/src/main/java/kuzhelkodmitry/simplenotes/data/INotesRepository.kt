package kuzhelkodmitry.simplenotes.data

import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

interface INotesRepository {

    fun getNotes(): Single<List<Note>>

    fun getNoteById(noteId: String): Single<Note>

    fun removeNote(noteId: String)

    fun createOrUpdateNote(note: Note)
}
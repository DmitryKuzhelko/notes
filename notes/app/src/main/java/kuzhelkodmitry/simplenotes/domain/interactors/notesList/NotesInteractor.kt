package kuzhelkodmitry.simplenotes.domain.interactors.notesList

import kuzhelkodmitry.simplenotes.data.INotesRepository
import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

class NotesInteractor(private val notesRepository: INotesRepository) : INotesInteractor {

    override fun getNotes(): Single<List<Note>> {
        return notesRepository.getNotes()
    }

    override fun removeNote(noteId: String) {
        notesRepository.removeNote(noteId)
    }

    override fun createOrUpdateNote(note: Note) {
        notesRepository.createOrUpdateNote(note)
    }
}
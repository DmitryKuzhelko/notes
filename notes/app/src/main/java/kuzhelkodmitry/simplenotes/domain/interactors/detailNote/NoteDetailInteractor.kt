package kuzhelkodmitry.simplenotes.domain.interactors.detailNote

import kuzhelkodmitry.simplenotes.data.INotesRepository
import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

class NoteDetailInteractor(private val notesRepository: INotesRepository) : INoteDetailInteractor {

    override fun getNoteById(noteId: String): Single<Note> {
        return notesRepository.getNoteById(noteId)
    }
}
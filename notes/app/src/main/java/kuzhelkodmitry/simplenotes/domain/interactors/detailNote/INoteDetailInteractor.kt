package kuzhelkodmitry.simplenotes.domain.interactors.detailNote

import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

interface INoteDetailInteractor {

    fun getNoteById(noteId: String): Single<Note>
}
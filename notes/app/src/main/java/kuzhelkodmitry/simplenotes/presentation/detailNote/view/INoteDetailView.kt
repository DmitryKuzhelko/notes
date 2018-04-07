package kuzhelkodmitry.simplenotes.presentation.detailNote.view

import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.presentation.base.view.IMvpView

interface INoteDetailView : IMvpView {
    fun fillInFields(note: Note)
}
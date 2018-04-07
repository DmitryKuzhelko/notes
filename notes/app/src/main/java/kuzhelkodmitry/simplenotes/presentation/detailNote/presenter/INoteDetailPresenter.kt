package kuzhelkodmitry.simplenotes.presentation.detailNote.presenter

import kuzhelkodmitry.simplenotes.presentation.base.presenter.IMvpPresenter
import kuzhelkodmitry.simplenotes.presentation.detailNote.view.INoteDetailView

interface INoteDetailPresenter<in V : INoteDetailView> : IMvpPresenter<V> {
    fun getDetailInfo(noteId: String)
}
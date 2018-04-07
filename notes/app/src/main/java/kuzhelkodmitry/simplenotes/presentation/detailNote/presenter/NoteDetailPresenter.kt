package kuzhelkodmitry.simplenotes.presentation.detailNote.presenter

import android.util.Log
import kuzhelkodmitry.simplenotes.ID_IS_EMPTY
import kuzhelkodmitry.simplenotes.domain.interactors.detailNote.NoteDetailInteractor
import kuzhelkodmitry.simplenotes.presentation.base.presenter.BasePresenter
import kuzhelkodmitry.simplenotes.presentation.detailNote.view.INoteDetailView
import javax.inject.Inject

class NoteDetailPresenter<V : INoteDetailView>
@Inject constructor(private val interactor: NoteDetailInteractor) : BasePresenter<V>(), INoteDetailPresenter<V> {

    override fun getDetailInfo(noteId: String) {
        if (noteId != ID_IS_EMPTY) {
            interactor.getNoteById(noteId).subscribe(
                    { note -> mvpView!!.fillInFields(note) },
                    { Log.i("NoteDetailPresenter", "Some error in getDetailInfo method") })
        }
    }
}
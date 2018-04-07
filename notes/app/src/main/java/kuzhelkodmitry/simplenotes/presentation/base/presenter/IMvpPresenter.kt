package kuzhelkodmitry.simplenotes.presentation.base.presenter

import kuzhelkodmitry.simplenotes.presentation.base.view.IMvpView

interface IMvpPresenter<in V : IMvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()
}
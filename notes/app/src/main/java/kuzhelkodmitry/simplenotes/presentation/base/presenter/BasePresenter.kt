package kuzhelkodmitry.simplenotes.presentation.base.presenter

import kuzhelkodmitry.simplenotes.presentation.base.view.IMvpView

open class BasePresenter<V : IMvpView> : IMvpPresenter<V> {

    protected var mvpView: V? = null

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView = null
    }
}
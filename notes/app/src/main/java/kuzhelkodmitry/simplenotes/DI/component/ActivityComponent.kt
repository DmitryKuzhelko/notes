package kuzhelkodmitry.simplenotes.DI.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kuzhelkodmitry.simplenotes.DI.modules.ActivityModule
import kuzhelkodmitry.simplenotes.presentation.detailNote.view.NoteDetailActivity
import kuzhelkodmitry.simplenotes.presentation.notesList.presenter.NotesPresenter
import kuzhelkodmitry.simplenotes.presentation.notesList.view.NotesActivity


@Component(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(notesActivity: NotesActivity)
    fun inject(noteDetailActivity: NoteDetailActivity)

    fun getNotesPresenter(): NotesPresenter<*>

    @Component.Builder
    interface Builder {
        fun build(): ActivityComponent

        @BindsInstance
        fun activity(context: Context): Builder
    }
}
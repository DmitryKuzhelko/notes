package kuzhelkodmitry.simplenotes.DI.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import kuzhelkodmitry.simplenotes.data.NotesRepository
import kuzhelkodmitry.simplenotes.domain.interactors.detailNote.NoteDetailInteractor
import kuzhelkodmitry.simplenotes.domain.interactors.notesList.NotesInteractor
import kuzhelkodmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter
import kuzhelkodmitry.simplenotes.presentation.detailNote.view.INoteDetailView
import kuzhelkodmitry.simplenotes.presentation.notesList.presenter.NotesPresenter
import kuzhelkodmitry.simplenotes.presentation.notesList.view.INotesView
import kuzhelkodmitry.simplenotes.presentation.notesList.view.NotesAdapter

@Module
class ActivityModule {

    //provides presenters
    @Provides
    fun provideNotesPresenter(context: Context, notesInteractor: NotesInteractor): NotesPresenter<*> = NotesPresenter<INotesView>(context, notesInteractor)

    @Provides
    fun provideNoteDetailPresenter(noteDetailInteractor: NoteDetailInteractor): NoteDetailPresenter<*> = NoteDetailPresenter<INoteDetailView>(noteDetailInteractor)

    //provides interactors
    @Provides
    fun provideNoteDetailInteractor(notesRepository: NotesRepository): NoteDetailInteractor = NoteDetailInteractor(notesRepository)

    @Provides
    fun provideNotesInteractor(notesRepository: NotesRepository): NotesInteractor = NotesInteractor(notesRepository)

    //provide repository
    @Provides
    fun provideNotesRepository(): NotesRepository = NotesRepository()

    //provide noteAdapter
    @Provides
    fun provideNotesAdapter(context: Context): NotesAdapter = NotesAdapter(context)
}
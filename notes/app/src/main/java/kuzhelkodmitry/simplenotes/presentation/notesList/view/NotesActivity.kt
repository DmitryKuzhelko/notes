package kuzhelkodmitry.simplenotes.presentation.notesList.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kuzhelkodmitry.simplenotes.*
import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.presentation.base.view.BaseActivity
import kuzhelkodmitry.simplenotes.presentation.notesList.presenter.NotesPresenter
import javax.inject.Inject

class NotesActivity : BaseActivity(), INotesView {

    @Inject
    lateinit var presenter: NotesPresenter<INotesView>

    @Inject
    lateinit var noteAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        presenter.onAttach(this@NotesActivity)
        setToolbar(resources.getString(R.string.all_notes))
        setAdapterClickListener()
        initRecyclerView()
        initFab()
    }

    override fun onStart() {
        super.onStart()
        presenter.setScreen()
    }

    override fun addNote() {
        noteAdapter.addNote()
    }

    override fun deleteNote(position: Int) {
        noteAdapter.removeNote(position)
    }

    override fun updateNote() {
        noteAdapter.updateNote()
    }

    override fun addDataToAdapter(notes: List<Note>) {
        noteAdapter.updateAdapter(notes)
    }

    override fun showEmptyScreen() {
        emptyScreen.visibility = View.VISIBLE
    }

    override fun hideEmptyScreen() {
        emptyScreen.visibility = View.GONE
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }
    }

    private fun initFab() {
        fab.setOnClickListener {
            presenter.addNote()
        }
    }

    private fun setAdapterClickListener() {
        noteAdapter.setClickListener(object : NotesAdapter.ClickListener {
            override fun onEditClick(note: Note, position: Int) {
                presenter.editNote(note.id, position)
            }

            override fun onDeleteClick(note: Note, position: Int) {
                presenter.deleteNote(note, position)
                presenter.setScreen()
            }
        })
    }

    override fun startDetailActivity(intent: Intent) {
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (data) {
            null -> return
            else -> presenter.createOrUpdateNote(
                    noteId = data.getStringExtra(NOTE_ID),
                    title = data.getStringExtra(NOTE_TITLE),
                    description = data.getStringExtra(NOTE_DESCRIPTION))
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
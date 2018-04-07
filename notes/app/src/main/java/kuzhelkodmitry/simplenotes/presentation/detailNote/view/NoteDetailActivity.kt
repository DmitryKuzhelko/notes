package kuzhelkodmitry.simplenotes.presentation.detailNote.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_note_detail.*
import kuzhelkodmitry.simplenotes.*
import kuzhelkodmitry.simplenotes.domain.entities.Note
import kuzhelkodmitry.simplenotes.presentation.base.view.BaseActivity
import kuzhelkodmitry.simplenotes.presentation.detailNote.presenter.NoteDetailPresenter
import javax.inject.Inject

class NoteDetailActivity : BaseActivity(), INoteDetailView {

    private lateinit var noteId: String

    @Inject
    lateinit var presenter: NoteDetailPresenter<INoteDetailView>

    companion object {
        fun getNoteDetailIntent(context: Context, noteId: String = ID_IS_EMPTY, position: Int = POSITION_IS_EMPTY): Intent =
                when (noteId) {
                    ID_IS_EMPTY -> Intent(context, NoteDetailActivity::class.java)
                    else -> Intent(context, NoteDetailActivity::class.java)
                            .putExtra(NOTE_ID, noteId)
                            .putExtra(POSITION_OF_LIST, position)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        activityComponent.inject(this)
        presenter.onAttach(this@NoteDetailActivity)

        setToolbar(resources.getString(R.string.add_note))

        noteId = intent.getStringExtra(NOTE_ID) ?: ID_IS_EMPTY
        presenter.getDetailInfo(noteId)
    }

    override fun fillInFields(note: Note) {
        etNoteTitle.setText(note.title)
        etNoteDescription.setText(note.description)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveNote -> {
                setResult(Activity.RESULT_OK,
                        Intent().apply {
                            putExtra(NOTE_ID, noteId)
                            putExtra(NOTE_TITLE, etNoteTitle.text.toString())
                            putExtra(NOTE_DESCRIPTION, etNoteDescription.text.toString())
                        })
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
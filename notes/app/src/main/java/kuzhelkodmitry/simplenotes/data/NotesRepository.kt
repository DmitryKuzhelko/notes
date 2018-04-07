package kuzhelkodmitry.simplenotes.data

import io.realm.Realm
import kuzhelkodmitry.simplenotes.NOTE_ID
import kuzhelkodmitry.simplenotes.domain.entities.Note
import rx.Single

class NotesRepository : INotesRepository {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun getNotes(): Single<List<Note>> =
            Single.fromCallable { realm.where(Note::class.java).findAll() }

    override fun getNoteById(noteId: String): Single<Note> =
            Single.fromCallable {
                realm.where(Note::class.java).equalTo(NOTE_ID, noteId).findFirst()
            }

    override fun removeNote(noteId: String) =
            realm.executeTransaction {
                realm.where(Note::class.java)
                        .equalTo(NOTE_ID, noteId).findFirst()!!.deleteFromRealm()
            }

    override fun createOrUpdateNote(note: Note) =
            realm.executeTransaction { realm.copyToRealmOrUpdate(note) }
}
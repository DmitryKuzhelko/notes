package kuzhelkodmitry.simplenotes.domain.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Note : RealmObject {

    @PrimaryKey
    @Required
    lateinit var id: String
    lateinit var title: String
    lateinit var description: String

    constructor()

    constructor(id: String = UUID.randomUUID().toString(), title: String, description: String) {
        this.id = id
        this.title = title
        this.description = description
    }
}
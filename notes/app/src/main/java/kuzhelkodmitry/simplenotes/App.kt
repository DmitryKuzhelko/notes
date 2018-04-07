package kuzhelkodmitry.simplenotes

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("my_db")
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
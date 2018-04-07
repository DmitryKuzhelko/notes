package kuzhelkodmitry.simplenotes.presentation.base.view

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import kuzhelkodmitry.simplenotes.DI.component.ActivityComponent
import kuzhelkodmitry.simplenotes.DI.component.DaggerActivityComponent
import kuzhelkodmitry.simplenotes.R


abstract class BaseActivity : AppCompatActivity(), IMvpView {

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activity(this)
                .build()
    }

    fun setToolbar(subtitle: String) {
        setSupportActionBar(toolbar)
        toolbar.apply {
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            setSubtitleTextColor(ContextCompat.getColor(context, R.color.white))
        }
        supportActionBar?.apply {
            title = resources.getString(R.string.app_name)
            setSubtitle(subtitle)
        }
    }
}
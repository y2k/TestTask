package com.example.testtask.view.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    fun addFragment(containerID: Int, fragment: Fragment, tag: String) {
        if (getFragmentByTag(tag) != null) {
            Timber.e("Fragment $tag is already added")
            return
        }
        supportFragmentManager
            .beginTransaction()
            .add(containerID, fragment, tag)
            .commit()
    }

    private fun getFragmentByTag(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }
}

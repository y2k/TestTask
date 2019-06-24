package com.example.testtask.view.activity

import android.os.Build
import android.widget.Toast
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

    //It's single activity app, so close Activity equals close app
    fun closeApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity()
        } else {
            this.finish()
        }
    }

    fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}

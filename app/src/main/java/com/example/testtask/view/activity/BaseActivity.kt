package com.example.testtask.view.activity

import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sdk.utils.isInternetAviable
import com.example.testtask.R
import com.example.testtask.view.dialog.NoConnectionDialog

abstract class BaseActivity : AppCompatActivity() {
    private val TAG_FRAGMENT_NO_CONNECTION: String = "NoConnectionTag"

    private lateinit var noInternetConnectionDialog: NoConnectionDialog

    //It's single activity app, so close Activity equals close app
    fun closeApp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity()
        } else {
            this.finish()
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showMessage(id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show()
    }

    //I'm not sure it clean enough. Maybe i should rewrite it later
    fun checkInternetConnection(onInternetStateListener: OnInternetStateListener) {
        if (isInternetAviable(this)) {
            onInternetStateListener.onSuccessConnection()
            if (::noInternetConnectionDialog.isInitialized) {
                noInternetConnectionDialog.dismiss()
            }
        } else {
            noInternetConnectionDialog = NoConnectionDialog(callBack = {
                if (it == NoConnectionDialog.NO_CONNECTION_EXIT) {
                    closeApp()
                } else if (it == NoConnectionDialog.NO_CONNECTION_OFFLINE) {
                    onInternetStateListener.onOfflineWorkClicked()
                } else {
                    if (!isInternetAviable(this)) {
                        showMessage(R.string.base_error_no_connection)
                    } else {
                        noInternetConnectionDialog.dismiss()
                        onInternetStateListener.onSuccessConnection()
                    }
                }
            })
            noInternetConnectionDialog.show(supportFragmentManager, TAG_FRAGMENT_NO_CONNECTION)
            noInternetConnectionDialog.isCancelable = false
        }
    }
}

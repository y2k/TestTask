package com.example.testtask.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.example.testtask.R
import kotlinx.android.synthetic.main.dialog_no_connection.*

class NoConnectionDialog(private val callBack: (id: Int) -> Unit) : DialogFragment() {

    companion object {
        const val NO_CONNECTION_EXIT: Int = 1
        const val NO_CONNECTION_RETRY: Int = 2
        const val NO_CONNECTION_OFFLINE: Int = 3
    }

    override fun onStart() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_no_connection, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, R.style.Dialog_DialogFragment)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_exit.setOnClickListener {
            callBack.invoke(NO_CONNECTION_EXIT)
        }

        btn_retry.setOnClickListener {
            callBack.invoke(NO_CONNECTION_RETRY)
        }

        btn_offline.setOnClickListener {
            callBack.invoke(NO_CONNECTION_OFFLINE)
        }
    }
}

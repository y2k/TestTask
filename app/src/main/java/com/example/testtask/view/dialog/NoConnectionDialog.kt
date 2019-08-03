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

class NoConnectionDialog : DialogFragment() {

    interface OnNoNetworkConnection {
        fun onRetryConnectionClick()
        fun onOfflineModeClick()
        fun onExitClick()
    }

    private lateinit var onNoNetworkConnectionImpl:OnNoNetworkConnection

    override fun onStart() {
        onNoNetworkConnectionImpl = activity as OnNoNetworkConnection
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_no_connection, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, R.style.Dialog_DialogFragment)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_retry.setOnClickListener {
            onNoNetworkConnectionImpl.onRetryConnectionClick()
        }

        btn_exit.setOnClickListener {
            onNoNetworkConnectionImpl.onExitClick()
        }

        btn_offline.setOnClickListener {
            onNoNetworkConnectionImpl.onOfflineModeClick()
        }
    }
}

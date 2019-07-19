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
import kotlinx.android.synthetic.main.dialog_error_connection.*

class ErrorConnectionDialog : DialogFragment() {

    companion object {
        private const val ARG_ERROR = "errorDialog_arg"

        fun getInstance(error: String) = ErrorConnectionDialog().apply {
            arguments = Bundle(2).apply {
                putString(ARG_ERROR, error)
            }
        }
    }

    override fun onStart() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_error_connection, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, R.style.Dialog_DialogFragment)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txt_error_connection_title.text =
            getString(R.string.connection_error_title, arguments?.get(ARG_ERROR) ?: "Unknown")
    }
}

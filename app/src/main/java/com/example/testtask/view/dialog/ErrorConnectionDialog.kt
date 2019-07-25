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
import com.example.testtask.view.beans.ErrorType
import com.example.testtask.view.beans.TestTaskError
import kotlinx.android.synthetic.main.dialog_error_connection.*

class ErrorConnectionDialog : DialogFragment() {

    companion object {
        private const val ARG_ERROR_TYPE = "errorDialog_type"
        private const val ARG_ERROR = "errorDialog_message"

        fun getInstance(error: TestTaskError) = ErrorConnectionDialog().apply {
            arguments = Bundle(2).apply {
                putSerializable(ARG_ERROR_TYPE, error.errorType)
                putString(ARG_ERROR, error.message)
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
        when (arguments?.getSerializable(ARG_ERROR_TYPE) as ErrorType) {
            ErrorType.DATABASE -> txt_error_connection_title.text =
                getString(R.string.error_database_select, arguments?.get(ARG_ERROR) ?: "Unknown")
            ErrorType.NETWORK -> txt_error_connection_title.text =
                getString(R.string.error_network_connect, arguments?.get(ARG_ERROR) ?: "Unknown")
        }
    }
}

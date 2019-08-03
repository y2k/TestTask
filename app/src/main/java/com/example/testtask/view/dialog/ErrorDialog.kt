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
import com.example.testtask.view.activity.MainActivity
import kotlinx.android.synthetic.main.dialog_error.*

class ErrorDialog : DialogFragment() {

    companion object {
        private const val ARG_FAILURE_MESSAGE = "failure_message"

        fun getInstance(errorMessage: String) = ErrorDialog().apply {
            arguments = Bundle(1).apply {
                putString(ARG_FAILURE_MESSAGE, errorMessage)
            }
        }
    }

    override fun onStart() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_error, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, R.style.Dialog_DialogFragment)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txt_error_connection_title.text = arguments?.getString(ARG_FAILURE_MESSAGE) ?: "Unknown"

        btn_close_app.setOnClickListener {
            dismiss()
            (activity as MainActivity).closeApp()
        }
    }
}

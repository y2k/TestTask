package com.example.testtask.view.fragment.additional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.example.testtask.R
import kotlinx.android.synthetic.main.fragment_no_connection.*

class NoConnectionDialogFragment(private val callBack: (id: Int) -> Unit) : DialogFragment() {

    companion object {
        const val NO_CONNECTION_EXIT: Int = 1
        const val NO_CONNECTION_RETRY: Int = 2
    }

    override fun onStart() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_no_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_exit.setOnClickListener {
            callBack.invoke(NO_CONNECTION_EXIT)
        }

        btn_retry.setOnClickListener {
            callBack.invoke(NO_CONNECTION_RETRY)
        }
    }
}

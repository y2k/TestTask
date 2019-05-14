package com.example.testtask.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.testtask.R
import com.example.testtask.extensions.fixName
import com.example.testtask.extensions.fromDateToFormattedString
import com.example.testtask.extensions.fromStringToDate
import com.example.testtask.extensions.getAge
import com.example.testtask.transport.SharedViewModel
import kotlinx.android.synthetic.main.fragment_employee.*

class EmployeeFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sharedViewModel.selectedEmployee.observe(this, Observer { employee ->

            text_employee_detail_name.text = employee.firstName?.fixName() ?: ""
            text_employee_detail_last_name.text = employee.lastName?.fixName() ?: ""

            if ((employee.birthday.isNullOrEmpty())) {
                text_employee_detail_birthday.text = getString(R.string.employee_age_empty)
                text_employee_detail_age.text = getString(R.string.employee_age_empty)
            } else {
                text_employee_detail_birthday.text = employee.birthday.fromStringToDate().fromDateToFormattedString()
                text_employee_detail_age.text = employee.birthday.fromStringToDate().getAge().toString()
            }
        })
    }
}

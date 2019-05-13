package com.example.testtask.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.testtask.R
import com.example.testtask.adapters.EmployeesAdapter
import com.example.testtask.adapters.SpecialitiesAdapter
import com.example.testtask.decorators.MarginItemDecoration
import com.example.testtask.model.Response
import com.example.testtask.transport.SharedViewModel
import com.example.testtask.verticalManager
import kotlinx.android.synthetic.main.fragment_employee.*
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.android.synthetic.main.fragment_speciality_list.*
import timber.log.Timber

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

            text_employee_detail_name.text = employee.firstName
            text_employee_detail_last_name.text = employee.lastName
            text_employee_detail_birthday.text = employee.birthday
            text_employee_detail_age.text = employee.birthday
        })
    }
}

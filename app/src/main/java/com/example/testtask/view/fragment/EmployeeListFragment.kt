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
import com.example.testtask.decorators.MarginItemDecoration
import com.example.testtask.model.Employee
import com.example.testtask.model.Specialty
import com.example.testtask.transport.SharedViewModel
import com.example.testtask.extensions.verticalManager
import kotlinx.android.synthetic.main.fragment_employee_list.*

class EmployeeListFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var specialty: Specialty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = EmployeesAdapter {
            navigateToEmployeeDetailInfo(it)
        }

        with(rv_employees) {
            layoutManager = verticalManager(context)
            addItemDecoration(
                MarginItemDecoration(
                    spaceTop = resources.getDimension(R.dimen.margin_8).toInt(),
                    spaceSide = resources.getDimension(R.dimen.margin_8).toInt(),
                    spaceBottom = resources.getDimension(R.dimen.margin_8).toInt()
                )
            )
            this.adapter = adapter
        }

        val specialtyID = arguments?.getInt("specialityID") ?: 0

        sharedViewModel.specialtyList.observe(this, Observer { list ->
            specialty = list[specialtyID]
            title_employees_speciality.text = specialty.specialityName
        })

        sharedViewModel.employeeList.observe(this, Observer { employeeList ->
            val employees = employeeList?.filter { employee -> employee.specialtyList?.contains(specialty) ?: false }
                ?: ArrayList()
            adapter.setEmployees(employees)
        }
        )
    }

    private fun navigateToEmployeeDetailInfo(employee: Employee) {
        sharedViewModel.selectedEmployee.value = employee
        Navigation.findNavController(activity!!, R.id.host).navigate(R.id.fromEmployeesListToEmployee)
    }
}

package com.example.testtask.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.sdk.extensions.inflate
import com.example.sdk.utils.verticalManager
import com.example.testtask.App
import com.example.testtask.Constants.Companion.KEY_SPECIALITY_ID
import com.example.testtask.R
import com.example.testtask.ReduxViewModel
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import com.example.testtask.view.adapters.EmployeesAdapter
import com.example.testtask.view.decorators.MarginItemDecoration
import com.example.testtask.view.viewmodel.Model
import com.example.testtask.view.viewmodel.Msg
import com.example.testtask.view.viewmodel.SharedViewModelKey
import kotlinx.android.synthetic.main.fragment_employee_list.*
import javax.inject.Inject

class EmployeeListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: ReduxViewModel<Model, Msg>

    private lateinit var speciality: Speciality

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().injector?.inject(this)
        sharedViewModel =
            ViewModelProviders.of(activity!!, factory).get(SharedViewModelKey::class.java) as ReduxViewModel<Model, Msg>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_employee_list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = EmployeesAdapter { navigateToEmployeeDetailInfo(it) }

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

        val specialtyID = arguments?.getInt(KEY_SPECIALITY_ID) ?: 0

//        sharedViewModel.specialtyListLiveData.observe(this, Observer { list ->
//            speciality = list[specialtyID]
//            title_employees_speciality.text = speciality.specialityName
//        })
//
//        sharedViewModel.employeeListLiveData.observe(this, Observer { employeeList ->
//            val employees = employeeList?.filter { employee ->
//                employee.specialtyList?.contains(speciality) ?: true
//            }
//            adapter.setEmployees(employees)
//        })

        sharedViewModel.state.observe(this, Observer { newState ->
            speciality = newState.specialtyList[specialtyID]
            title_employees_speciality.text = speciality.specialityName

            val employees = newState.employeeList.filter { employee ->
                employee.specialtyList.contains(speciality)
            }
            adapter.setEmployees(employees)
        })
    }

    private fun navigateToEmployeeDetailInfo(employee: Employee) {
        sharedViewModel.dispatch(Msg.SetSelectedEmployee(employee))
        Navigation.findNavController(activity!!, R.id.host).navigate(R.id.fromEmployeesListToEmployee)
    }
}

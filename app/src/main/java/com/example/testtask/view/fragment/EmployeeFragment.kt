package com.example.testtask.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.sdk.extensions.fromDateToFormattedString
import com.example.sdk.extensions.fromStringToDate
import com.example.sdk.extensions.getAge
import com.example.sdk.extensions.inflate
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.ReduxViewModel
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.viewmodel.Model
import com.example.testtask.view.viewmodel.Msg
import com.example.testtask.view.viewmodel.SharedViewModelKey
import kotlinx.android.synthetic.main.fragment_employee.*
import javax.inject.Inject

class EmployeeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: ReduxViewModel<Model, Msg>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().injector?.inject(this)
        sharedViewModel =
            ViewModelProviders.of(activity!!, factory).get(SharedViewModelKey::class.java) as ReduxViewModel<Model, Msg>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_employee)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.state.observe(this, Observer {
            val employee = it.selectedEmployee ?: return@Observer
            text_employee_detail_name.text = employee.firstName
            text_employee_detail_last_name.text = employee.lastName

            if ((employee.birthday.isEmpty())) {
                text_employee_detail_birthday.text = getString(R.string.employee_age_empty)
                text_employee_detail_age.text = getString(R.string.employee_age_empty)
            } else {
                text_employee_detail_birthday.text = employee.birthday.fromStringToDate().fromDateToFormattedString()
                text_employee_detail_age.text = employee.birthday.fromStringToDate().getAge().toString()
            }

            Glide.with(this).load(employee.avatarUrl)
                .placeholder(ColorDrawable(Color.GREEN))
                .error(R.drawable.ic_error)
                .into(avatar)
        })
    }
}

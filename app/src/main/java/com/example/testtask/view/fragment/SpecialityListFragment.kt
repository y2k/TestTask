package com.example.testtask.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.sdk.utils.isInternetAviable
import com.example.sdk.utils.verticalManager
import com.example.testtask.App

import com.example.testtask.R
import com.example.testtask.adapters.SpecialitiesAdapter
import com.example.testtask.decorators.MarginItemDecoration
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.transport.SharedViewModel
import com.example.testtask.view.fragment.additional.NoConnectionFragment
import kotlinx.android.synthetic.main.fragment_speciality_list.*
import timber.log.Timber
import javax.inject.Inject

class SpecialityListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().injector?.inject(this)
        sharedViewModel = ViewModelProviders.of(this, factory).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speciality_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = SpecialitiesAdapter {
            navigateToEmployeeFragment(it)
        }

        with(rv_specialities) {
            layoutManager = verticalManager(context)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.margin_8).toInt(),
                    resources.getDimension(R.dimen.margin_8).toInt(),
                    resources.getDimension(R.dimen.margin_8).toInt()
                )
            )
            this.adapter = adapter
        }

        sharedViewModel.specialtyList.observe(this, Observer { list ->
            adapter.setSpecialities(list)
        })
    }

    private fun navigateToEmployeeFragment(specialtyID: Int) {
        val bundle = Bundle()
        bundle.putInt("specialityID", specialtyID)
        Navigation.findNavController(activity!!, R.id.host).navigate(R.id.fromSpecialityListToEmployeeList, bundle)
    }
}

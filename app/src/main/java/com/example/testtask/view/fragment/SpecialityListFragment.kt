package com.example.testtask.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.sdk.extensions.inflate
import com.example.sdk.extensions.verticalManager
import com.example.testtask.App
import com.example.testtask.Constants.Companion.KEY_SPECIALITY_ID

import com.example.testtask.R
import com.example.testtask.view.adapters.SpecialitiesAdapter
import com.example.testtask.view.decorators.MarginItemDecoration
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.view.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_speciality_list.*
import javax.inject.Inject

class SpecialityListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.get().injector?.inject(this)
        sharedViewModel = ViewModelProviders.of(activity!!, factory).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_speciality_list)
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

        sharedViewModel.specialtyListLiveData.observe(this, Observer { list ->
            adapter.setSpecialities(list)
        })
    }

    private fun navigateToEmployeeFragment(specialtyID: Int) {
        val bundle = Bundle()
        bundle.putInt(KEY_SPECIALITY_ID, specialtyID)
        Navigation.findNavController(activity!!, R.id.host).navigate(R.id.fromSpecialityListToEmployeeList, bundle)
    }
}

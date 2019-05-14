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
import com.example.testtask.adapters.SpecialitiesAdapter
import com.example.testtask.decorators.MarginItemDecoration
import com.example.testtask.transport.SharedViewModel
import com.example.testtask.extensions.verticalManager
import kotlinx.android.synthetic.main.fragment_speciality_list.*

class SpecialityListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speciality_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = SpecialitiesAdapter {
            navigateToEmployeeFragment(it)
        }

        with(rv_specialities) {
            layoutManager = verticalManager(context)
            addItemDecoration(MarginItemDecoration(
                    resources.getDimension(R.dimen.margin_8).toInt(),
                    resources.getDimension(R.dimen.margin_8).toInt(),
                    resources.getDimension(R.dimen.margin_8).toInt()))
            this.adapter = adapter
        }

        val sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
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

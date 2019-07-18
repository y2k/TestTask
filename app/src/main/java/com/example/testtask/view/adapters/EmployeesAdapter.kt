package com.example.testtask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sdk.extensions.fixName
import com.example.sdk.extensions.fromStringToDate
import com.example.sdk.extensions.getAge
import com.example.testtask.R
import com.example.testtask.data.model.EmployeeNetwork
import kotlinx.android.synthetic.main.cell_employees.view.*
import kotlinx.android.synthetic.main.cell_specialities.view.cell_root
import kotlin.collections.ArrayList

class EmployeesAdapter(private val callback: (employeeNetwork: EmployeeNetwork) -> Unit) :
        RecyclerView.Adapter<EmployeesAdapter.EmployeeHolder>() {

    private var employeeList = ArrayList<EmployeeNetwork>()

    fun setEmployees(employeeNetworks: List<EmployeeNetwork>?) {
        if (employeeNetworks != null) {
            employeeList = employeeNetworks as ArrayList<EmployeeNetwork>
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EmployeeHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return EmployeeHolder(inflater.inflate(R.layout.cell_employees, viewGroup, false))
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    inner class EmployeeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var name = itemView.cell_employee_name
        private var lastName = itemView.cell_employee_last_name
        private var age = itemView.cell_employee_age
        private var root = itemView.cell_root

        fun bind(employeeNetwork: EmployeeNetwork) {
            name.text = employeeNetwork.firstName?.fixName()
            lastName.text = employeeNetwork.lastName?.fixName()

            if (employeeNetwork.birthday.isNullOrEmpty()) {
                age.text = itemView.context.getString(R.string.employee_age_empty)
            } else age.text = employeeNetwork.birthday.fromStringToDate().getAge().toString()

            root.setOnClickListener {
                callback.invoke(employeeList[adapterPosition])
            }
        }
    }
}

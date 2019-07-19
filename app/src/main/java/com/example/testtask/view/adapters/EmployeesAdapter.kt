package com.example.testtask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sdk.extensions.fromStringToDate
import com.example.sdk.extensions.getAge
import com.example.testtask.R
import com.example.testtask.domain.model.Employee
import kotlinx.android.synthetic.main.cell_employees.view.*
import kotlinx.android.synthetic.main.cell_specialities.view.cell_root
import kotlin.collections.ArrayList

class EmployeesAdapter(private val callback: (employee: Employee) -> Unit) :
    RecyclerView.Adapter<EmployeesAdapter.EmployeeHolder>() {

    private var employeeList = ArrayList<Employee>()

    fun setEmployees(employees: List<Employee>?) {
        if (employees != null) {
            employeeList = employees as ArrayList<Employee>
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

        fun bind(employee: Employee) {
            name.text = employee.firstName
            lastName.text = employee.lastName

            if (employee.birthday.isNullOrEmpty()) {
                age.text = itemView.context.getString(R.string.employee_age_empty)
            } else age.text = employee.birthday?.fromStringToDate()?.getAge().toString()

            root.setOnClickListener {
                callback.invoke(employeeList[adapterPosition])
            }
        }
    }
}

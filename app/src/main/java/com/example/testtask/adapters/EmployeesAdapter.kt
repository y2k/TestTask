package com.example.testtask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.model.Employee
import kotlinx.android.synthetic.main.cell_employees.view.*
import kotlinx.android.synthetic.main.cell_specialities.view.cell_root
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import org.joda.time.Years
import org.joda.time.LocalDate

class EmployeesAdapter(private val callback: (employee: Employee) -> Unit) :
    RecyclerView.Adapter<EmployeesAdapter.EmployeeHolder>() {

    private var employeeList = ArrayList<Employee>()

    fun setEmployees(employees: List<Employee>) {
        employeeList = employees as ArrayList<Employee>
        notifyDataSetChanged()
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

            when {
                (employee.birthday.isNullOrEmpty()) -> age.text = " «\u00AD« "
                else -> age.text = getAgeFromDate(getDateFromString(employee.birthday)).toString()
            }
            root.setOnClickListener {
                callback.invoke(employeeList[adapterPosition])
            }
        }

        private fun getDateFromString(date: String): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            var convertedDate = Date()
            try {
                convertedDate = dateFormat.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return convertedDate
        }

        private fun getAgeFromDate(date: Date): Int {
            val c = Calendar.getInstance()
            c.time = date

            val day = c.get(Calendar.DAY_OF_MONTH)
            val daymonth = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val birthdate = LocalDate(year, daymonth + 1, day)
            val now = LocalDate()
            val age = Years.yearsBetween(birthdate, now)
            return age.years
        }
    }
}

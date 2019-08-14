package com.example.testtask.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sdk.extensions.fixBirthday
import com.example.sdk.extensions.fixName
import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.IO
import com.example.testtask.SIO
import com.example.testtask.Store
import com.example.testtask.data.datasource.network.ResponseResult
import com.example.testtask.data.toDomain
import com.example.testtask.domain.Services
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import com.example.testtask.update
import retrofit2.HttpException

data class Model(
    val employeeList: List<Employee> = arrayListOf(),
    val specialtyList: List<Speciality> = arrayListOf(),
    val selectedEmployee: Employee? = null,
    val progressBar: Boolean = false,
    val errorLiveData: Failure? = null
)

sealed class Msg {
    object SetData : Msg()
    class SetDataComplete(val result: Either<HttpException, ResponseResult>?) : Msg()
    class SetSelectedEmployee(val employee: Employee) : Msg()
    class StoreChanged(val db: Store) : Msg()
}

object SharedViewModel {

    fun sub(db: Store): Msg = Msg.StoreChanged(db)

    fun update(model: Model, msg: Msg): Pair<Model, IO<Msg>?> = when (msg) {
        is Msg.SetData ->
            model.copy(progressBar = true) to
                suspend { Store.dispatch(::mkEmployRequest)().let(Msg::SetDataComplete) }
        is Msg.SetDataComplete ->
            model.copy(progressBar = false) to
                suspend { Store.update { db -> applyEmployees(db, msg.result) }; null }
        is Msg.SetSelectedEmployee ->
            model to suspend { Store.update { it.copy(selectedEmployee = msg.employee) }; null }
        is Msg.StoreChanged -> reduceView(msg.db, model) to null
    }

    fun mkEmployRequest(db: Store): SIO<Either<HttpException, ResponseResult>?> =
        if (db.cachedEmployees.isEmpty()) {
            db to if (db.isOfflineModeEnabled) suspend { null } else suspend { Services.api.loadData() }
        } else {
            updateCacheSpecs(db, db.cachedEmployees) to suspend { Services.api.loadData() }
        }

    fun applyEmployees(db: Store, employeeResult: Either<*, ResponseResult>?) =
        when (employeeResult) {
            is Either.Data -> {
                val employees = employeeResult.data.items.map {
                    it.toDomain().apply {
                        firstName = firstName.fixName()
                        lastName = lastName.fixName()
                        birthday = birthday.fixBirthday()
                    }
                }
                updateCacheSpecs(db, employees).copy(cachedEmployees = employees)
            }
            else -> db
        }

    fun updateCacheSpecs(db: Store, employees: List<Employee>) =
        if (db.cachedSpecialities.isEmpty()) {
            val specialties = employees
                .flatMap { it.specialtyList }
                .distinct()
            db.copy(cachedSpecialities = specialties)
        } else {
            db
        }

    fun reduceView(db: Store, state: Model) =
        when (db.employeesListResult) {
            is Either.Data ->
                state.copy(
                    employeeList = db.employeesListResult.data,
                    specialtyList = db.cachedSpecialities,
                    selectedEmployee = db.selectedEmployee
                )
            is Either.Error ->
                state.copy(errorLiveData = db.employeesListResult.error)
            null -> state
        }.copy(selectedEmployee = db.selectedEmployee)
}

class SharedViewModelKey : ViewModel()

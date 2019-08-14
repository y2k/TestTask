package com.example.testtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sdk.other.Either
import com.example.sdk.other.Failure
import com.example.testtask.domain.model.Employee
import com.example.testtask.domain.model.Speciality
import java.io.Closeable

data class Store(
    val selectedEmployee: Employee? = null,
    val cachedEmployees: List<Employee> = listOf(),
    val cachedSpecialities: List<Speciality> = emptyList(),
    val isOfflineModeEnabled: Boolean = false,
    val employeesListResult: Either<Failure, List<Employee>>? = null
) {
    companion object : F<Store>()
}

abstract class F<S> {
    @Deprecated("")
    suspend fun <T> dispatch(f: (S) -> Pair<S, T>): T = TODO()

    @Deprecated("")
    fun subscribe(f: (S) -> Unit): Closeable = TODO()
}

@Deprecated("")
suspend fun <S, T> F<S>.read(f: (S) -> T): T = TODO()

@Deprecated("")
suspend fun <S> F<S>.update(f: (S) -> S): Unit = TODO()

open class ReduxViewModel<Model, Msg>(
    vm: Model,
    sub: (db: Store) -> Msg,
    update: (model: Model, msg: Msg) -> Pair<Model, IO<Msg>?>
) : ViewModel() {

    fun dispatch(msg: Msg): Unit = TODO()

    val state = MutableLiveData<Model>()
}

typealias IO<T> = suspend () -> T?
typealias UIO = suspend () -> Unit
typealias SIO<T> = Pair<Store, suspend () -> T>

package com.example.testtask.view.viewmodel

import com.example.sdk.core.network.NetworkHelper
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.interactor.SpecialityInteractorImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class SharedViewModelTest {

    @Mock lateinit var sharedViewModel: SharedViewModel
    @Mock lateinit var networkHelper: NetworkHelper
    @Mock lateinit var employeeInteractorImpl:EmployeeInteractorImpl
    @Mock lateinit var specialityInteractorImpl: SpecialityInteractorImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.sharedViewModel = SharedViewModel(networkHelper,employeeInteractorImpl,specialityInteractorImpl)
    }

    @Test
    fun getEmployeeListLiveData() {
    }

    @Test
    fun getSpecialtyListLiveData() {
    }

    @Test
    fun getSelectedEmployeeLiveData() {
    }

    @Test
    fun getProgressBarLiveData() {
    }

    @Test
    fun getErrorLiveData() {
    }

    @Test
    fun getNetworkStatusLiveData() {
    }

    @Test
    fun setData() {
    }

    @Test
    fun setSelectedEmployee() {
    }
}
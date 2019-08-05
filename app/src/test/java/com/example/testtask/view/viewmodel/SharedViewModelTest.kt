package com.example.testtask.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sdk.core.network.NetworkHelperImpl
import com.example.testtask.AndroidTest
import com.example.testtask.App
import com.example.testtask.Config
import com.example.testtask.data.datasource.network.ApiService
import com.example.testtask.data.datasource.network.ResponseResult
import com.example.testtask.data.model.EmployeeNetwork
import com.example.testtask.domain.interactor.EmployeeInteractorImpl
import com.example.testtask.domain.interactor.SpecialityInteractorImpl
import com.example.testtask.domain.model.Employee
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.microedition.khronos.opengles.GL

class SharedViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock private lateinit var networkHelper: NetworkHelperImpl
    @Mock private lateinit var employeeInteractor: EmployeeInteractorImpl
    @Mock private lateinit var specialityInteractor: SpecialityInteractorImpl

    //TODO:Find a way to inject it with Dagger2
    private val client = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Config.BASE_URL)
        .build()

    private val api = client.create(ApiService::class.java)

    private lateinit var sharedViewModel: SharedViewModel

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        sharedViewModel = SharedViewModel(networkHelper, employeeInteractor, specialityInteractor)
    }

    //TODO: Make this test more valuable
    @Test fun `check is json correct`(){
        val result = runBlocking { api.loadData().await().items }
        result shouldNotBe  null
        result.size shouldNotBe 0
        result.forEach{println(it)}
    }
}
package com.example.testtask.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testtask.network.GitlabApiService
import com.example.testtask.repository.EmployeeRepository
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        val TAG_SPEC_LIST = "SPEC_LIST"
    }

    object EmployeeRepositoryProvider {
        fun provideEmployeeRepository(apiService: GitlabApiService): EmployeeRepository {
            return EmployeeRepository(apiService)
        }
    }

    fun addFragment(containerID: Int, fragment: Fragment, tag: String) {
        if (getFragmentByTag(tag) != null) {
            Timber.e("Fragment $tag is already added")
            return
        }
        supportFragmentManager
            .beginTransaction()
            .add(containerID, fragment, tag)
            .commit()
    }

    private fun getFragmentByTag(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }
}

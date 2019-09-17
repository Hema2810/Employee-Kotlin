package com.example.employee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    val employees: LiveData<List<Employee>>

    init {
        val mDb = EmployeeDatabase.getInstance(this.getApplication())
        employees = mDb!!.employeeDao().loadEmployees()
    }
}

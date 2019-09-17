package com.example.employee

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.os.Bundle
import android.view.View

import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var layoutManager: LinearLayoutManager? = null
   // private var mRecyclerView: RecyclerView? = null
    internal val mAdapter = EmployeeAdapter(this)
    private val mEmployees = ArrayList<Employee>()
    internal var mDb: EmployeeDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        rv_employees.layoutManager = layoutManager
        mDb = EmployeeDatabase.getInstance(applicationContext)


        floating_action_button.setOnClickListener {
            val intent = Intent(this@MainActivity, EmployeeDetails::class.java)

            startActivity(intent)
        }


        val viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        viewModel.employees.observe(this, Observer { employees ->
            if (employees != null) {
                mEmployees.clear()
                mEmployees.addAll(employees)

            }
            mAdapter.setEmployees(mEmployees)
            rv_employees.adapter = mAdapter
        })

    }
}

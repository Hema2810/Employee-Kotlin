package com.example.employee

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    fun loadEmployees(): LiveData<List<Employee>>

    /* @Query("SELECT * FROM MovieInfo WHERE id LIKE :id")
    MovieInfo findByMovieId(int id);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employeeList: Employee)

    @Delete
    fun deleteEmployee(employeeList: Employee)
}

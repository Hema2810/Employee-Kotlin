package com.example.employee

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {

        private val LOCK = Any()
        private val DATABASE_NAME = "EmployeeDBKotlin"
        private var sInstance: EmployeeDatabase? = null


        fun getInstance(context: Context): EmployeeDatabase? {

            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = Room.databaseBuilder(context.applicationContext,
                            EmployeeDatabase::class.java,
                            EmployeeDatabase.DATABASE_NAME)
                            .build()

                }
            }
            return sInstance
        }
    }
}

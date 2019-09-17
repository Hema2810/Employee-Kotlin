package com.example.employee

import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.content.Intent
import android.content.IntentFilter

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_employee_details.*



class EmployeeDetails : AppCompatActivity() {

    internal var mDb: EmployeeDatabase? = null
    internal var avatarString: String? = "R.drawable.download"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)


        val nameString = tv_EmployeeName.text.toString()
        val ageString = tv_EmployeeAge.text.toString()

        mDb = EmployeeDatabase.getInstance(applicationContext)

        iv_EmployeeAvatar.setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent, "Select Image")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
            startActivityForResult(chooserIntent, PICK_IMAGE)
        }

        add.setOnClickListener {
            val nameString = tv_EmployeeName.text.toString()
            val ageString = tv_EmployeeAge.text.toString()
            val empAge = Integer.parseInt(ageString)
            val emp = Employee(nameString, empAge, avatarString!!)
            AppExecutors.instance!!.diskIO().execute {
                mDb!!.employeeDao().insertEmployee(emp)
                finish()
            }
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       // val avatar = findViewById<ImageView>(R.id.iv_EmployeeAvatar)

        if (resultCode == PICK_IMAGE) {
            avatarString = data!!.dataString.toString()

            Picasso.with(iv_EmployeeAvatar.context)
                    .load(avatarString)
                    .placeholder(R.drawable.download)
                    .error(R.drawable.download)
                    .into(iv_EmployeeAvatar)


        }
    }

    companion object {
        val PICK_IMAGE = 1
    }

}

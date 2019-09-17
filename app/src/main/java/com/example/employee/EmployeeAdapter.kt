package com.example.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.employee_detail.view.*

import java.util.ArrayList

internal class EmployeeAdapter(private val mContext: Context) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var mEmployeeList: ArrayList<Employee>? = null


    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val ePosition = position


        Picasso.with(holder.mAvatarImageView.context)
                .load(mEmployeeList!![position].avatar)
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(holder.mAvatarImageView)

        //holder.mAvatarImageView.setColorFilter(android.R.color.black);

        holder.mNameTextView.text = mEmployeeList!![position].name
        val i = mEmployeeList!![position].age
        holder.mAgeTextView.text = "" + mEmployeeList!![position].age

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        //val mContext = parent.context
        val layoutIdForEmployee = R.layout.employee_detail
       /* val inflater = LayoutInflater.from(mContext)
        val attachToParentImmediately = false*/

        val view = mContext.layoutInflater.inflate(layoutIdForEmployee, parent, false)
        return EmployeeViewHolder(view)
    }


    fun setEmployees(employeeList: ArrayList<Employee>) {
        mEmployeeList = employeeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mEmployeeList!!.size
    }

    internal inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mNameTextView = itemView.tv_EmployeeName
        val mAvatarImageView = itemView.iv_EmployeeAvatar
        val mAgeTextView = itemView.tv_EmployeeAge

       /* val mAvatarImageView: ImageView
        val mNameTextView: TextView
        val mAgeTextView: TextView


        init {
            mAvatarImageView = itemView.findViewById(R.id.iv_EmployeeAvatar)
            mNameTextView = itemView.findViewById(R.id.tv_EmployeeName)
            mAgeTextView = itemView.findViewById(R.id.tv_EmployeeAge)

        }*/


    }

    companion object {

        private val IMAGEVIEW_HEIGHT_RATIO = 1.5.toFloat()
    }
}




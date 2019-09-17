package com.example.employee

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
/*import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
        var imageId: Int,
        var title: String,
        var price: Double,
        var category: String
      ) : Parcelable}*/

@Entity
class Employee : Parcelable {

      @PrimaryKey
      var name: String
      var age: Int = 0
      private var mAvatar: String? = null

      var avatar: String?
            get() = mAvatar
            set(avatar) {
                  name = mAvatar.toString()
            }

      companion object CREATOR : Parcelable.Creator<Employee> {
            override fun createFromParcel(parcel: Parcel): Employee {
                  return Employee(parcel)
            }

            override fun newArray(size: Int): Array<Employee?> {
                  return arrayOfNulls(size)
            }

      }
      constructor(name: String, age: Int, avatar: String) {

            this.name = name
            this.age = age
            this.mAvatar = avatar
      }

      private constructor(`in`: Parcel) {
            name = `in`.readString().toString()
            age = `in`.readInt()
            mAvatar = `in`.readString()

      }

      override fun describeContents(): Int {
            return 0
      }

      override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(name)
            dest.writeInt(age)
            dest.writeString(mAvatar)

      }

      /* companion object {


        val CREATOR: Parcelable.Creator<Employee> = object : Parcelable.Creator<Employee> {
            override fun createFromParcel(`in`: Parcel): Employee {
                return Employee(`in`)
            }

            override fun newArray(size: Int): Array<Employee> {
                return arrayOfNulls(size)
            }
        }
    }*/


}
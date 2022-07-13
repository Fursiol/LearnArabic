package com.learnarabic.hierarchy

import android.os.Parcel
import android.os.Parcelable



data class Form( val id:Int,
                val uniC: Char ) :  Parcelable {

    // Parcelizacja
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt().toChar()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(uniC.code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Form> {
        override fun createFromParcel(parcel: Parcel): Form {
            return Form(parcel)
        }

        override fun newArray(size: Int): Array<Form?> {
            return arrayOfNulls(size)
        }
    }
}

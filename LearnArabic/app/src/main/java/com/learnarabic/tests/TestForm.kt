package com.learnarabic.tests

import android.os.Parcel
import android.os.Parcelable
// klasa przechowuje dane jednej formy w specyfikacji przygotowanej do testu
class TestForm(
    private val id: Int,
    private val name: String,
    private val symbol: String): Parcelable
{


     fun getId():Int { return this.id }

     fun getName(): String { return  this.name }

     fun getSymbol(): String { return this.symbol }


    //parcelizacja
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestForm> {
        override fun createFromParcel(parcel: Parcel): TestForm {
            return TestForm(parcel)
        }

        override fun newArray(size: Int): Array<TestForm?> {
            return arrayOfNulls(size)
        }
    }


}
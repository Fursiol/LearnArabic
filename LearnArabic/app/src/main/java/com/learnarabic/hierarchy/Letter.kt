package com.learnarabic.hierarchy

import android.os.Parcel
import android.os.Parcelable
import com.learnarabic.tools.ItemsViewModel


data class Letter(
    val isolated: Form?,
    val initial: Form?,
    val medial: Form?,
    val final: Form?,
    val name: String?,
    val vocalization: String?) :Parcelable {

    //Parcelizacja
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Form::class.java.classLoader),
        parcel.readParcelable(Form::class.java.classLoader),
        parcel.readParcelable(Form::class.java.classLoader),
        parcel.readParcelable(Form::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(isolated, flags)
        parcel.writeParcelable(initial, flags)
        parcel.writeParcelable(medial, flags)
        parcel.writeParcelable(final, flags)
        parcel.writeString(name)
        parcel.writeString(vocalization)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Letter> {
        override fun createFromParcel(parcel: Parcel): Letter {
            return Letter(parcel)
        }

        override fun newArray(size: Int): Array<Letter?> {
            return arrayOfNulls(size)
        }
    }
}

fun letterToItemsViewModel(l :Letter): ItemsViewModel =
    ItemsViewModel(
        l.isolated?.uniC.toString(),
        l.initial?.uniC.toString(),
        l.medial?.uniC.toString(),
        l.final?.uniC.toString(),
        l.name!!
    )
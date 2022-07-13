package com.learnarabic.main

import android.os.Parcel
import android.os.Parcelable
import com.learnarabic.hierarchy.Alphabet
import com.learnarabic.tests.Test

class App(
    val alphabetData: Alphabet,
    var quiz: Test,
    var testType: String = "",
    var formType: String = "",
    var saves: String = "saves.txt",
    private var score: Int =0
):Parcelable  {



     fun getScore(): Int {return this.score}

     fun scoreUp(){
        this.score += 1
    }


    fun getAppData():String{
        return ("testType = $testType; formType = $formType")
    }

    //Parcelizacja
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Alphabet::class.java.classLoader)!!,
        parcel.readParcelable(Test::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

     override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(alphabetData, flags)
        parcel.writeParcelable(quiz, flags)
        parcel.writeString(testType)
        parcel.writeString(formType)
        parcel.writeString(saves)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<App> {
        override fun createFromParcel(parcel: Parcel): App {
            return App(parcel)
        }

        override fun newArray(size: Int): Array<App?> {
            return arrayOfNulls(size)
        }
    }
}


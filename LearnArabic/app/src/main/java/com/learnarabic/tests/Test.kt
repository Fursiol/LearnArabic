package com.learnarabic.tests

import android.os.Parcel
import android.os.Parcelable
import com.learnarabic.R.string
import com.learnarabic.hierarchy.Alphabet
import java.lang.Exception
import kotlin.random.Random

//klasa przechowujaca i generujaca instancje testu
open class Test() : Parcelable {


    private var list = emptyList<TestForm>()
    open fun getFromList(id : Int) : TestForm {return this.list.get(id) }

    private val alphabet :Alphabet
        get() = Alphabet()

    private var size = 0
    open fun getSize(): Int {return  this.size}
    open fun setSize(newSize: Int) {this.size}



    //funkcja wypełajaca listy nowa instancja i losuje id poprawnej odpowiedzi
    open fun drawNewTestList(size: Int, formType: String){
        val tmp = alphabet.getAlphabetAsItemsViewModel()
        val ids = drawIDs(size, formType)
        val r = mutableListOf<TestForm>()
        for (item in ids){
            var s = ""
            when (formType){
                string.isolatedForm.toString() ->s = tmp.get(item/4).textIsolated
                string.initialForm.toString() -> s= tmp.get(item/4).textInitial
                string.medialForm.toString() -> s = tmp.get(item/4).textMedial
                string.finalForm.toString() -> s = tmp.get(item/4).textFinal
            }
            r.add(TestForm(item,tmp.get(item/4).textName,s))
        }
        this.list = r.toList()
    }

    // funkcja sprawdza odpowiedz
    open fun test(chosenAnswer : Int, correctAnswer : Int, formType: String): Boolean {
        //obsluga wyjatkow
        try {
            if (formType != "isolatedForm" && formType != "finalForm" && formType != "medialForm" && formType != "initialForm")
                throw Exception("Incorrect form type")
        }
        catch(e : Exception){
            System.err.println("Incorrect form type in function Test.test")
            return false
        }
        try {
            if (chosenAnswer < 1 || chosenAnswer > 112 || correctAnswer < 1 || chosenAnswer > 112)
                throw Exception("Form id out of range")
        }
        catch (e: Exception){
            System.err.println("Form id out of range in function Test.test")
            return false
        }
        return chosenAnswer == correctAnswer
    }

    //generuje liste, o rozmiarze size niepowtarzalnych id o formie podanej jko formType, musi byc conajmniej 1 element, max 26
    fun drawIDs(size :Int, formType: String) : List<Int>{
        try {
            if(size < 2 || size > 26)
                throw Exception("Wrong size")
        }
        catch (e: Exception){
            System.err.println("Wrong size in function Test.generateListOfFormIDs")
            return emptyList()
        }

        //tworze liste i dodaje 1 element
        val exitList = mutableListOf(randomFormID(formType))

        //tyle jescze musze wylosowac
        var s = size -1
        //bede dodawał elementy az s==0
        while (s > 0){
            var b = true
            //petla do losowania niepowtarzalnego id
            var tmp = randomFormID(formType)
            while (b){
                tmp = randomFormID(formType)
                b = false
                for (item in exitList){
                    if (item == tmp){
                        b=true}
                }
            }
            exitList += tmp
            s--
        }
        return exitList
    }


    //generuje id losowe id dla formy
    fun randomFormID(formType: String): Int{
        val adder: Int
        try {
            adder = when (formType){
                string.isolatedForm.toString() -> 0
                string.initialForm.toString() -> 1
                string.medialForm.toString() -> 2
                string.finalForm.toString() -> 3
                else -> throw Exception("Wrong value of formType")
            }
        }
        catch ( e: Exception){
            System.err.println("Wrong value of formType in function Test.generateListOfFormIDs")
            return 0
        }
        return (Random.nextInt(0,27) *4 ) + adder + 1
    }


    //parcelizacja
    constructor(parcel: Parcel) : this() {
        list = parcel.createTypedArrayList(TestForm)!!
        size = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(list)
        parcel.writeInt(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Test> {
        override fun createFromParcel(parcel: Parcel): Test {
            return Test(parcel)
        }

        override fun newArray(size: Int): Array<Test?> {
            return arrayOfNulls(size)
        }
    }

}
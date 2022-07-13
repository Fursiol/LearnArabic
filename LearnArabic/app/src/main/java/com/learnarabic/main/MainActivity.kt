package com.learnarabic.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button


import com.learnarabic.R
import com.learnarabic.R.layout.menu_layout
import com.learnarabic.ShowAlphabet
import com.learnarabic.TestMenu

import com.learnarabic.hierarchy.Alphabet
import com.learnarabic.tests.Test


class MainActivity : AppCompatActivity(){

    //funkcja obsługująca przełączenie goToAlphabet button
    private fun goToAlphabet( app: App){
        val intent = Intent(this,ShowAlphabet::class.java)
        intent.putExtra("name", app)
        startActivity(intent)
    }
    // funkcja obsługująca GoToTest button
    private fun goToTest( app:App){
        val intent = Intent(this,TestMenu::class.java)
        intent.putExtra("testmenu", app)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(menu_layout)

        val app = App(Alphabet(), Test())

        //Alphabet button
        findViewById<Button>(R.id.GoToAlphabet).setOnClickListener{
            goToAlphabet(app)
        }

        //test button
        findViewById<Button>(R.id.GoToTest).setOnClickListener {
            goToTest(app)
        }


  }
}
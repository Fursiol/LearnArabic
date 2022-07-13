package com.learnarabic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.learnarabic.main.App

class TestMenu : AppCompatActivity() {

    //funkcja obsługujaca nacisniecie przycisku i wybranie rodzaju testu
    private fun chooseTest(app: App?, testStirng: String){
        app?.testType = testStirng
        println(app?.getAppData())
        val intent = Intent(this,FormMenu::class.java)
        intent.putExtra("formmenu", app)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_menu)

        val app = intent.getParcelableExtra<App>("testmenu")

        findViewById<Button>(R.id.ChooseNameTest).setOnClickListener {
            chooseTest(app, R.string.nameTest.toString())
        }
        findViewById<Button>(R.id.ChooseSymbolTest).setOnClickListener {
            chooseTest(app, R.string.symbolTest.toString())
        }
        findViewById<Button>(R.id.ChooseVocalizationTest).setOnClickListener {
            chooseTest(app, R.string.vocalizationTest.toString())
        }
    }
}
package com.learnarabic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.learnarabic.main.App
class FormMenu : AppCompatActivity() {
    //funkcja obsugujaca naicskanie przycisku i wybranie formy
    private fun chooseTest( app: App?, formStirng: String){
        app?.formType = formStirng
        println(app?.getAppData())
        val intent = Intent(this,TestView::class.java)
        intent.putExtra("testview", app)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_menu)

        val app = intent.getParcelableExtra<App>("formmenu")

        findViewById<Button>(R.id.CheckIsolated).setOnClickListener {
            chooseTest(app,R.string.isolatedForm.toString())
        }
        findViewById<Button>(R.id.CheckInitial).setOnClickListener {
            chooseTest(app,R.string.initialForm.toString())
        }
        findViewById<Button>(R.id.CheckMedial).setOnClickListener {
            chooseTest(app,R.string.medialForm.toString())
        }
        findViewById<Button>(R.id.CheckFinal).setOnClickListener {
            chooseTest(app,R.string.finalForm.toString())
        }

    }
}
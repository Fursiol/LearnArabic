package com.learnarabic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.learnarabic.main.App
import java.io.File
import kotlin.random.Random

class TestView : AppCompatActivity() {

    private fun porcess(f : Int, s: Int, a: App) :Boolean{
        return if (f == s){
            a.scoreUp()
            println("$f $s tak")
            true
        } else{
            println("$f $s nie")
            false
        }
    }
    //funkcja wpisujaca do pliku logi testu
    private fun logToFile(a:App, cA : Int, wA: Int){
        File(this.filesDir, a.saves).createNewFile()
        File(this.filesDir, a.saves).writeText(
                    "${a.testType};${a.formType};${a.getScore()};$cA;$wA"
        )
    }

    private fun processTest( app: App) {
        setContentView(R.layout.activity_test_view)
        val size = 4
        app.quiz.drawNewTestList(4, app.formType)

        //przyciski
        val correctA = findViewById<TextView>(R.id.CorrectAnswer)
        val answer0 = findViewById<Button>(R.id.Answer0)
        val answer1 = findViewById<Button>(R.id.Answer1)
        val answer2 = findViewById<Button>(R.id.Answer2)
        val answer3 = findViewById<Button>(R.id.Answer3)
        val sV = findViewById<TextView>(R.id.ScoreValue)
        val wa = findViewById<TextView>(R.id.ShowWrongAnswer)
        sV.text = app.getScore().toString()


        val tmp = Random.nextInt(0,size-1)
        when (app.testType){

            //podana nazwa znajdz symbol
            R.string.nameTest.toString() ->{
                correctA.text = app.quiz.getFromList(tmp).getName()
                answer0.text = app.quiz.getFromList(0).getSymbol()
                answer1.text = app.quiz.getFromList(1).getSymbol()
                answer2.text = app.quiz.getFromList(2).getSymbol()
                answer3.text = app.quiz.getFromList(3).getSymbol()
            }
            //podany symbol zanjdz nazwe
            R.string.symbolTest.toString() ->{
                correctA.text = app.quiz.getFromList(tmp).getSymbol()
                answer0.text = app.quiz.getFromList(0).getName()
                answer1.text = app.quiz.getFromList(1).getName()
                answer2.text = app.quiz.getFromList(2).getName()
                answer3.text = app.quiz.getFromList(3).getName()

            }
            R.string.vocalizationTest.toString() ->{
                setContentView(R.layout.work_in_progres)
            }
        }

            answer0.setOnClickListener {
                if (porcess(
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(0).getId(), app
                    )
                ) {
                    sV.text = app.getScore().toString()
                    this.onStart()
                }
                else{
                    wa.text = getString(R.string.wrong_answer)
                    logToFile(
                        app,
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(0).getId())
                }
            }
            answer1.setOnClickListener {
                if (porcess(
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(1).getId(), app
                    )
                ) {
                    sV.text = app.getScore().toString()
                    this.onStart()
                }
                else{
                    wa.text = getString(R.string.wrong_answer)
                    logToFile(
                        app,
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(1).getId())
                }
            }
            answer2.setOnClickListener {
                if (porcess(
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(2).getId(), app
                    )
                ) {
                    sV.text = app.getScore().toString()
                    this.onStart()
                }
                else{
                    wa.text = getString(R.string.wrong_answer)
                    logToFile(
                        app,
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(2).getId())
                }
            }
            answer3.setOnClickListener {
                if (porcess(
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(3).getId(), app
                    )
                )  {
                    sV.text = app.getScore().toString()
                    this.onStart()
                }
                else{
                    wa.text = getString(R.string.wrong_answer)
                    logToFile(
                        app,
                        app.quiz.getFromList(tmp).getId(),
                        app.quiz.getFromList(3).getId())
                }
            }

        }

    override fun onStart() {
        super.onStart()
        val app = intent.getParcelableExtra<App>("testview")
        processTest( app!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.onStart()

//
    }


}
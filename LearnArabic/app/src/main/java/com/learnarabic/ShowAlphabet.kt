package com.learnarabic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learnarabic.main.App
import com.learnarabic.tools.CustomAdapter

class ShowAlphabet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_alphabet)

        val app = intent.getParcelableExtra<App>("name")

        val t: RecyclerView = findViewById(R.id.recycler)
        t.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = CustomAdapter(app!!.alphabetData.getAlphabetAsItemsViewModel())
        t.adapter = adapter
    }
}
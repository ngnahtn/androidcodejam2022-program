package com.example.affirmations

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.DataSource

class MainActivity : AppCompatActivity() {
    private val dataSource = DataSource().dataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configNavigate()

        // init dataSource
        val recyclerView: RecyclerView = findViewById(R.id.recycle_view)
        recyclerView.adapter = ItemAdapter(this, dataSource)

        recyclerView.setHasFixedSize(true)
    }

    private fun configNavigate() {
        // hide navigation top bar
        supportActionBar?.hide()
    }
}
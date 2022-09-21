/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.adapter.LetterAdapter
import com.example.wordsapp.adapter.OnLetterButtonListener
import com.example.wordsapp.constant.Constant
import com.example.wordsapp.databinding.ActivityMainBinding
import java.util.zip.Inflater

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        chooseLayout()
        val adapter = LetterAdapter()
        adapter.buttonListener = object : OnLetterButtonListener {
            override fun onClickButton(letter: String) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(Constant.LETTER, letter)
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter

    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
    }

    /**
     * create options Menu and its components
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu,menu)
        // find button_switch
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // set layout for menuButtonItem
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // defind when switch button is selected
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /**
     * set Icon for menu Item
     * */
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        if (isLinearLayoutManager) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        } else {
            menuItem.icon =  ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        }
    }
}

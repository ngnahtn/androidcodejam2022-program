/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogglers.adapter.DogCardAdapter
import com.example.dogglers.adapter.DogListener
import com.example.dogglers.const.Layout
import com.example.dogglers.databinding.ActivityVerticalListBinding
import com.example.dogglers.model.Dog
import vn.onesk.common.toJson

class VerticalListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerticalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = DogCardAdapter(
            applicationContext,
            Layout.VERTICAL
        )
        adapter.dogListenner = object : DogListener {
            override fun onClickDog(dog: Dog) {
                val intent = Intent(this@VerticalListActivity,DogDetailActivity::class.java)
                intent.putExtra("dogJSON", dog.toJson())
                startActivity(intent)

            }

        }
        binding.verticalRecyclerView.adapter = adapter

        // Specify fixed size to improve performance
        binding.verticalRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
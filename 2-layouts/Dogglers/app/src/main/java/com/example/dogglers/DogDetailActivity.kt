package com.example.dogglers

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.dogglers.databinding.DogDetailBinding
import com.example.dogglers.model.Dog
import vn.onesk.common.jsonToListObject
import vn.onesk.common.jsonToObject


class DogDetailActivity : AppCompatActivity() {

    //    private lateinit var binding:
    private lateinit var binding: DogDetailBinding

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dogJSONString = intent.getStringExtra("dogJSON")
        val dogObject = dogJSONString.jsonToObject(Dog::class.java)

        if (dogObject != null) {
       binding.itemImageView.setImageResource(dogObject.imageResourceId)
                binding.dogName.text = dogObject.name
                binding.dogAge.text = getString(R.string.dog_age, dogObject.age)
                binding.dogHobbies.text = getString(R.string.dog_hobbies,dogObject.hobbies)
        }

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
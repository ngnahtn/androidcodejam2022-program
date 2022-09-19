package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // late initialization of binding variable
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // lauch the binding to access the activity.file
        binding = ActivityMainBinding.inflate(layoutInflater)

//        Set up the component that displays the content of the activity
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    /**
    handle calculate Tip
    * */
    fun calculateTip() {
        val string = binding.costOfServiceEditText.text.toString()
        var selectedId = binding.tipOptions.checkedRadioButtonId

        // check textView.text is not null
        if (string != "") {
            val cost: Double? = string.toDoubleOrNull()
            // check if convertToDouble is null
            if (cost == null) {
                binding.tipResult.text = "pls type your cost"
                return
            }
            // defind tipPercent
            var discount = when (selectedId) {
                R.id.amazing_service -> 0.2
                R.id.good_service -> 0.18
                else -> 0.15
            }
            var tip = cost * discount
            // check if round tip
            if (binding.roundSwitch.isChecked == true) {
                tip = kotlin.math.ceil(tip)
            }
            displayTip(tip)
        }
    }

    /**
    handle display Tip
    **/
    fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
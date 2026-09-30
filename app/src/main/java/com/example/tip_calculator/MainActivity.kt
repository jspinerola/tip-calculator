package com.example.tip_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var baseEditText: EditText
    private lateinit var tipPercentEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var tipAmountTextView: TextView
    private lateinit var totalAmountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        baseEditText = findViewById(R.id.baseEditText)
        tipPercentEditText = findViewById(R.id.tipPercentEditText)
        calculateButton = findViewById(R.id.calculateButton)
        tipAmountTextView = findViewById(R.id.tipAmountTextView)
        totalAmountTextView = findViewById(R.id.totalAmountTextView)

        calculateButton.setOnClickListener {
            calculateTip()
        }

        calculateTip()
    }

    private fun calculateTip() {
        val baseString = baseEditText.text.toString()
        val base = baseString.toDoubleOrNull() ?: 0.0

        val tipPercentString = tipPercentEditText.text.toString()
        val tipPercent = tipPercentString.toDoubleOrNull() ?: 0.0

        val tip = base * (tipPercent / 100.0)
        val total = base + tip

        val format = NumberFormat.getCurrencyInstance()
        tipAmountTextView.text = getString(R.string.tip_amount_format, format.format(tip))
        totalAmountTextView.text = getString(R.string.total_amount_format, format.format(total))
    }
}

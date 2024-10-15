package com.example.calculatorll

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentOperator: String = ""
    private var firstOperand: Double = 0.0
    private var secondOperand: Double = 0.0
    private var isOperatorClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the display text view
        display = findViewById(R.id.Text)

        // Number buttons
        val buttons = listOf(
            findViewById<Button>(R.id.Button07), findViewById<Button>(R.id.button04),
            findViewById<Button>(R.id.button01), findViewById<Button>(R.id.button0)
        )

        // Set listeners for number buttons
        for (button in buttons) {
            button.setOnClickListener { onNumberClick((it as Button).text.toString()) }
        }

        // Operator buttons
        findViewById<Button>(R.id.buttonPlus).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.buttonX).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.buttonSlide).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.buttonEqual).setOnClickListener { onEqualClick() }
        findViewById<Button>(R.id.buttonCE).setOnClickListener { onClearEntryClick() }
        findViewById<Button>(R.id.buttonC).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.buttonDot).setOnClickListener { onDotClick() }
        findViewById<Button>(R.id.buttonBS).setOnClickListener { onBackspaceClick() }
        findViewById<Button>(R.id.buttonPaM).setOnClickListener { onPlusMinusClick() }
    }

    // Handle number button click
    private fun onNumberClick(value: String) {
        if (display.text == "0" || isOperatorClicked) {
            display.text = value
        } else {
            display.append(value)
        }
        isOperatorClicked = false
    }

    // Handle operator button click
    private fun onOperatorClick(operator: String) {
        firstOperand = display.text.toString().toDouble()
        currentOperator = operator
        isOperatorClicked = true
    }

    // Handle equal button click
    private fun onEqualClick() {
        secondOperand = display.text.toString().toDouble()
        val result = when (currentOperator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> firstOperand / secondOperand
            else -> 0.0
        }
        display.text = result.toString()
        isOperatorClicked = true
    }

    // Clear the current entry
    private fun onClearEntryClick() {
        display.text = "0"
    }

    // Clear all
    private fun onClearClick() {
        display.text = "0"
        currentOperator = ""
        firstOperand = 0.0
        secondOperand = 0.0
        isOperatorClicked = false
    }

    // Add a dot for decimal numbers
    private fun onDotClick() {
        if (!display.text.contains(".")) {
            display.append(".")
        }
    }

    // Handle backspace click
    private fun onBackspaceClick() {
        val text = display.text.toString()
        if (text.isNotEmpty()) {
            display.text = text.dropLast(1)
        }
        if (display.text.isEmpty()) {
            display.text = "0"
        }
    }

    // Handle plus/minus button click
    private fun onPlusMinusClick() {
        val value = display.text.toString().toDouble()
        display.text = (-value).toString()
    }
}

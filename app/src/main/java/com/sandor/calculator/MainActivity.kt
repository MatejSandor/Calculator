package com.sandor.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }
    private lateinit var result: EditText
    private lateinit var newNumber: EditText

    //Variables to hold operands
    private var operand1: Double? = null
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //Buttons with numbers initialization
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val buttonDot = findViewById<Button>(R.id.buttonDecimal)

        //Buttons for operations
        val plus: Button = findViewById(R.id.buttonAddition)
        val minus: Button = findViewById(R.id.buttonSubtraction)
        val times: Button = findViewById(R.id.buttonMultiplication)
        val divide: Button = findViewById(R.id.buttonDivision)
        val equals: Button = findViewById(R.id.buttonEquals)

        // Creation of listener for buttons
        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val operationListener = View.OnClickListener { v ->
            val o = (v as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, o)
            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }
            val value = newNumber.text.toString()
            pendingOperation = o
            displayOperation.text = pendingOperation
        }

        plus.setOnClickListener(operationListener)
        minus.setOnClickListener(operationListener)
        times.setOnClickListener(operationListener)
        divide.setOnClickListener(operationListener)
        equals.setOnClickListener(operationListener)

    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
        }
        when (pendingOperation) {
            "=" -> operand1 = value
            "+" -> operand1 = operand1!! + value
            "-" -> operand1 = operand1!! - value
            "*" -> operand1 = operand1!! * value
            "/" -> operand1 = if (value == 0.0) {
                Double.NaN
            } else {
                operand1!! / value
            }
        }
        result.setText(operand1.toString())
        newNumber.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}

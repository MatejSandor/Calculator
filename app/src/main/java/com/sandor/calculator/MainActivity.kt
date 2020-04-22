package com.sandor.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }
    private lateinit var result: EditText
    private lateinit var newNumber: EditText

    //Variables to hold operands
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //Buttons with numbers initialization
        val button0= findViewById<Button>(R.id.button0)
        val button1= findViewById<Button>(R.id.button1)
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
    }
}

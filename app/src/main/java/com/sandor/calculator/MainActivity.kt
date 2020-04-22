package com.sandor.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private var operand2: Double = 0.0
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById<EditText>(R.id.result)
        newNumber = findViewById<EditText>(R.id.newNumber)

        //buttons with numbers initialization
        val button0: Button = findViewById<Button>(R.id.button0)
        val button1: Button = findViewById<Button>(R.id.button1)
        val button2: Button = findViewById<Button>(R.id.button2)
        val button3: Button = findViewById<Button>(R.id.button3)
        val button4: Button = findViewById<Button>(R.id.button4)
        val button5: Button = findViewById<Button>(R.id.button5)
        val button6: Button = findViewById<Button>(R.id.button6)
        val button7: Button = findViewById<Button>(R.id.button7)
        val button8: Button = findViewById<Button>(R.id.button8)
        val button9: Button = findViewById<Button>(R.id.button9)
        val buttonDot: Button = findViewById<Button>(R.id.buttonDecimal)

        //buttons for operations
        val plus: Button = findViewById(R.id.buttonAddition)
        val minus: Button = findViewById(R.id.buttonSubtraction)
        val times: Button = findViewById(R.id.buttonMultiplication)
        val divide: Button = findViewById(R.id.buttonDivision)
        val equals: Button = findViewById(R.id.buttonEquals)
    }
}

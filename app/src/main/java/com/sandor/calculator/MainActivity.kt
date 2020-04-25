package com.sandor.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_PENDING_OPERATION = "StatePendingOperation"
private const val STATE_OPERAND1 = "Operand1"

class MainActivity : AppCompatActivity() {

    //Variables to hold operands
    private var operand1: Double? = null
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        buttonDecimal.setOnClickListener(listener)

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
            operation.text = pendingOperation
        }

        buttonAddition.setOnClickListener(operationListener)
        buttonSubtraction.setOnClickListener(operationListener)
        buttonMultiplication.setOnClickListener(operationListener)
        buttonDivision.setOnClickListener(operationListener)
        buttonEquals.setOnClickListener(operationListener)

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
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION, "")
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1, 0.0)
        operation.text = pendingOperation
    }
}

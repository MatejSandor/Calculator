package com.sandor.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    //Variables to hold operands
    private var operand1: Double? = null
    private var pendingOperation = "="

    private val result = MutableLiveData<String>()
    private val newNumber = MutableLiveData<String>()
    private val operation = MutableLiveData<String>()

    fun digitPressed(caption: String) {
        if(newNumber.value != null) {
            newNumber.value = newNumber.value + caption
        } else {
            newNumber.value = caption
        }
    }

    fun operandPressed(op: String) {
        try {
            val value = newNumber.value?.toDouble()
            if (value != null) {
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value = ""
        }
        val value = newNumber.value.toString()
        pendingOperation = op
        operation.value = pendingOperation
    }

    fun negPressed() {
        val value = newNumber.value
        if (value == null || value.isEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                var doubleValue = value.toDouble()
                doubleValue *= -1
                newNumber.value = doubleValue.toString()
            } catch (e: NumberFormatException) {
                newNumber.value = ""
            }
        }
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
        result.value = operand1.toString()
        newNumber.value = ""
    }

}
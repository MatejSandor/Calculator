package com.sandor.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: BigDecimalViewModel by viewModels()
        viewModel.stringResult.observe(
            this,
            Observer<String> { stringResult -> result.setText(stringResult) })
        viewModel.stringNumber.observe(
            this,
            Observer<String> { stringNumber -> newNumber.setText(stringNumber) })
        viewModel.stringOperation.observe(
            this,
            Observer<String> { stringOperation -> operation.text = stringOperation })

        // Creation of listener for buttons
        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
            viewModel.operandPressed((v as Button).text.toString())
        }

        buttonAddition.setOnClickListener(operationListener)
        buttonSubtraction.setOnClickListener(operationListener)
        buttonMultiplication.setOnClickListener(operationListener)
        buttonDivision.setOnClickListener(operationListener)
        buttonEquals.setOnClickListener(operationListener)

        buttonSign?.setOnClickListener {
            viewModel.negPressed()
        }

    }
}

package com.example.dietform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.core.view.children
import androidx.core.view.forEach
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchDiet.setOnCheckedChangeListener { _, onSwitch ->
            if (!onSwitch) {
                enableBtns(false)
                txtResult.text = "No dietary restrictions."
            } else {
                enableBtns(true)
                txtResult.text = "Dietary restrictions. "
            }
        }
        btnSubmit.setOnClickListener {
            var result = "Dietary restrictions. "
            val radioButtonId = radioGroup.checkedRadioButtonId
            if (radioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(radioButtonId)
                result += selectedRadioButton.text.toString() + ". "
            }
            if (chkGluten.isChecked) {
                result += chkGluten.text.toString() + ". "
            }
            if (chkLactose.isChecked) {
                result += chkLactose.text
            }
            txtResult.text = result
        }

        btnClear.setOnClickListener {
            clearBtns()
            txtResult.text = ""
        }
    }

    private fun clearBtns() {
        radioGroup.clearCheck()
        chkGluten.isChecked = false
        chkLactose.isChecked = false
    }

    private fun enableBtns(enable: Boolean) {
        clearBtns()
        for (btn in radioGroup.children) {
            btn.isEnabled = enable
        }
        chkGluten.isEnabled = enable
        chkLactose.isEnabled = enable
        btnSubmit.isEnabled = enable
        btnClear.isEnabled = enable
    }
}

package com.tranconguet.democustominputfield

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customInputField = findViewById<CustomInputField>(R.id.customInputField)
        customInputField.setSuffixIconOnClickListener {
            Log.d("Test", "Suffix Item onClick and current inputText is: ${customInputField.getText()}")
        }
        findViewById<Button>(R.id.btnTest).setOnClickListener {
            val isInputFieldError = customInputField.getIsError()
            Log.d("Test", "Change input field state to ${!isInputFieldError}")
            customInputField.setIsError(!isInputFieldError)
        }
    }
}
package com.example.isbn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class Start : AppCompatActivity()
{
    lateinit var button : Button
    /*lateinit var radioButton: RadioButton
    lateinit var radioButton2: RadioButton*/
    var radioButtonGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        button = findViewById(R.id.button2)

        radioButtonGroup = findViewById(R.id.radioButtonGroup)

        /*radioButton = findViewById(R.id.radioButtonKönyv)
        radioButton2 = findViewById(R.id.radioButtonSzerző)*/

        button.setOnClickListener {

            val selectedOption: Int = radioButtonGroup!!.checkedRadioButtonId


            if(selectedOption != -1)
            {
                radioButton = findViewById(selectedOption)
                Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(baseContext,"Null", Toast.LENGTH_SHORT).show()
        }
    }
}
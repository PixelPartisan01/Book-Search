package com.example.isbn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Start : AppCompatActivity()
{
    lateinit var button : Button
    /*lateinit var radioButton: RadioButton
    lateinit var radioButton2: RadioButton*/
    var radioButtonGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        button = findViewById(R.id.button2)

        radioButtonGroup = findViewById(R.id.radioButtonGroup)
        editText = findViewById(R.id.editText)


        button.setOnClickListener {

            val selectedOption: Int = radioButtonGroup!!.checkedRadioButtonId

            if(selectedOption != -1)
            {
                radioButton = findViewById(selectedOption)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Auth_Boo", editText.text.toString())
                intent.putExtra("Radio_Butt", radioButton.text)
                startActivity(intent)
            }
            else
                Toast.makeText(baseContext, "Szerzőre vagy Könyvre Keresel?", Toast.LENGTH_LONG).show()
        }
    }
}
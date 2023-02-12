package com.example.bmi

import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weighttext =findViewById<EditText>(R.id.wtnum)
        val heighttext =findViewById<EditText>(R.id.htnum)
        val calbutton =findViewById<Button>(R.id.calbtn)

        calbutton.setOnClickListener{
            val weight = weighttext.text.toString()
            val  height = heighttext.text.toString()
            if(validateInput(weight,height)) {
                val BMI = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //gets bmi in 2 decimal values
                val bmi2digits = String.format("%.2f", BMI).toFloat()
                displayresult(BMI)
            }
        }
    }
    private fun validateInput(weight:String? ,height : String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this," Error!! Weight is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this," Error!! height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayresult(BMI: Float) {
        val index= findViewById<TextView>(R.id.tvindex)
        val  result = findViewById<TextView>(R.id.tvresult)
        val info =findViewById<TextView>(R.id.tvnfo)

        index.text= BMI.toString()
        info.text="(Normal Range is 18.5 to 24.9)"
         var resulttext =""
        var color =0

        when{
            BMI<18.50->{
                resulttext="Under weight"
                color = R.color.underweight
            }
            BMI in 18.50..24.99->{
                resulttext="Normal weight"
                color = R.color.normal
            }
            BMI in 25.00..29.99->{
                resulttext="Over weight"
                color = R.color.overweight
            }
            BMI > 29.99->{
                resulttext="Obese"
                color = R.color.obese
            }
        }
        result.setTextColor(ContextCompat.getColor(this,color))
        result.text=resulttext

    }

}
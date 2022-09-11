package com.pablopg.caloriecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.result_screen.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Calculadora de calorías")
        setContentView(R.layout.activity_main)

        btnCalc.setOnClickListener{
            val indSexo: Int = rgSexo.checkedRadioButtonId
            val edad: Int = etEdad.getText().toString().toInt()
            val altura: Double = etAltura.getText().toString().toDouble()
            val peso: Double = etPeso.getText().toString().toDouble()
            val indObjetivo: Int = rgObjetivo.checkedRadioButtonId
            val indEntre: Int = rgEntrenos.checkedRadioButtonId
            if(indSexo != -1
                && etEdad.getText().toString().isNotEmpty()
                && etAltura.getText().toString().isNotEmpty()
                && etPeso.getText().toString().isNotEmpty()
                && indObjetivo != -1
                && indEntre != -1
            ){
                val sexo: String = findViewById<RadioButton>(indSexo).text.toString()
                val objetivo: Double = findViewById<RadioButton>(indObjetivo).text.toString().toDouble()
                val entrenamientos: Double = findViewById<RadioButton>(indEntre).text.toString().toDouble()

                val mb: Double
                if(sexo.equals("Hombre"))
                    mb = 10*peso+6.25*altura-5*edad+5
                else if(sexo.equals("Mujer"))
                    mb = 10*peso+6.25*altura-5*edad-161
                else
                    mb = 0.0

                val calMantenimiento: Double = mb*entrenamientos

                val resFinal: Double = calMantenimiento*objetivo

                setContentView(R.layout.result_screen)

                tvMetBasal.setText("Metabolismo basal\n         " +(mb*100).roundToInt()/100 +" kcal")
                tvCObj.setText("Requerimiento objetivo\n            " +(resFinal*100).roundToInt()/100 +" kcal")
                tvCMan.setText("Calorías de mantenimiento: " +calMantenimiento.roundToInt() +" kcal")
            }
        }
    }
}
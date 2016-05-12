package com.example.raulcorchero.grosscleaner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_setting.*

class Setting : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        inicializador()
    }

    fun inicializador() {
        txtIRPFMan.isEnabled = false

        val arraySpinner = arrayOf( "Escoja una opción",
                                    "Soltero, Viudo, Divorciado o Separado",
                                    "Con cónyugue (ingresos de este <1500 euros al año",
                                    "Otros")

        val s = findViewById(R.id.cmbSituacion) as Spinner
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner)
        s.adapter = adapter
    }

    fun setIRPFMAN(v: View) {
        if(this.tbIRPF.isChecked()) {
            txtIRPFMan.setEnabled(true)
            cmbSituacion.setEnabled(false)
            txtGradoDis.setText("")
            txtGradoDis.setEnabled(false)
            txtDescend.setText("")
            txtDescend.setEnabled(false)
            txtDescMen3.setText("")
            txtDescMen3.setEnabled(false)
            ckbRedViv.setChecked(false)
            ckbRedViv.setEnabled(false)
        }else{
            txtIRPFMan.setText("")
            txtIRPFMan.setEnabled(false)
            cmbSituacion.setEnabled(true)
            txtGradoDis.setEnabled(true)
            txtDescend.setEnabled(true)
            txtDescMen3.setEnabled(true)
            ckbRedViv.setEnabled(true)
        }

    }
}

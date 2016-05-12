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



        val arraySpinner = arrayOf("1", "2", "3", "4", "5")

        val s = findViewById(R.id.cmbSituacion) as Spinner
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner)
        s.adapter = adapter
    }

    fun setIRPFMAN(v: View) {
        txtIRPFMan.setEnabled(this.tbIRPF.isChecked())
    }
}

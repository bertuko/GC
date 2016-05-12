package com.example.raulcorchero.grosscleaner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_setting.*

class Setting : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        inicializador()
    }

    fun inicializador() {
        txtIRPFMan.isEnabled = false
    }

    fun setIRPFMAN(v: View) {
        txtIRPFMan.setEnabled(this.tbIRPF.isChecked())
    }
}

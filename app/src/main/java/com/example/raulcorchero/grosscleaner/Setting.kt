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

        var u = Utilities(this.getBaseContext())
        if (u.ExistsUserdata()){
            cargarDatos(u.LoadUserdata())
        } else {
            cargarDatos(User())
        }

    }

    override fun onPause () {
        super.onPause()
        var usuario: User = User()
        //Recuperamos datos de la pantalla al usuario
        usuario.FuerzaRetencion = this.tbIRPF.isChecked()
        usuario.PorcentajeRetencion = initalizeValue(this.txtIRPFMan.getText().toString()).toFloat()
        usuario.Horas = initalizeValue(this.txtHoras.getText().toString()).toInt()
        usuario.SituacionFamiliar = this.cmbSituacion.selectedItemPosition
        //usuario.EscalaDiscapacidad = initalizeValue(this.txtGradoDis.getText().toString()).toInt()
        usuario.GradoDiscapacidad = initalizeValue(this.txtGradoDis.getText().toString()).toInt()
        usuario.NumDescendientesMenores3 = initalizeValue(this.txtDescMen3.getText().toString()).toInt()
        usuario.NumDescendientesMayores3 = initalizeValue(this.txtDescend.getText().toString()).toInt()
        usuario.ReduccionVivienda = this.ckbRedViv.isChecked()

        var u = Utilities(this.getBaseContext())
        u.saveUserdata(usuario)
    }

    private fun initalizeValue (datastring: String): String{
        var r: String = datastring.trim()
        if (r == "") {r = "0"}
        return r
    }

    fun setIRPFMAN(v: View) {
        if(this.tbIRPF.isChecked()) {
            this.txtIRPFMan.setEnabled(true)
            this.cmbSituacion.setEnabled(false)
            this.txtGradoDis.setText("")
            this.txtGradoDis.setEnabled(false)
            this.txtDescend.setText("")
            this.txtDescend.setEnabled(false)
            this.txtDescMen3.setText("")
            this.txtDescMen3.setEnabled(false)
            this.ckbRedViv.setChecked(false)
            this.ckbRedViv.setEnabled(false)
        }else{
            this.txtIRPFMan.setText("")
            this.txtIRPFMan.setEnabled(false)
            this.cmbSituacion.setEnabled(true)
            this.txtGradoDis.setEnabled(true)
            this.txtDescend.setEnabled(true)
            this.txtDescMen3.setEnabled(true)
            this.ckbRedViv.setEnabled(true)
        }

    }

    private fun cargarDatos(usuario: User){
        if (usuario.FuerzaRetencion) {
            this.txtIRPFMan.setEnabled(true)
            this.cmbSituacion.setEnabled(false)
            this.txtGradoDis.setEnabled(false)
            this.txtDescend.setEnabled(false)
            this.txtDescMen3.setEnabled(false)
            this.ckbRedViv.setChecked(false)
            this.ckbRedViv.setEnabled(false)
        }else{
            this.txtIRPFMan.setEnabled(false)
            this.txtIRPFMan.setEnabled(false)
            this.cmbSituacion.setEnabled(true)
            this.txtGradoDis.setEnabled(true)
            this.txtDescend.setEnabled(true)
            this.txtDescMen3.setEnabled(true)
            this.ckbRedViv.setEnabled(true)
        }
        this.txtHoras.setText(usuario.Horas.toString())
        this.cmbSituacion.setSelection(usuario.SituacionFamiliar)
        this.txtGradoDis.setText(usuario.GradoDiscapacidad.toString())
        this.txtDescMen3.setText(usuario.NumDescendientesMenores3.toString())
        this.txtDescend.setText(usuario.NumDescendientesMayores3.toString())
        this.ckbRedViv.setChecked(usuario.ReduccionVivienda)
    }
}

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

        var usuario: User = User()
        var u = Utilities(this.getBaseContext())
        if (u.ExistsUserdata()){
            usuario = u.LoadUserdata()
        }

        //Mostramos los datos en la pantalla
        cargarDatos(usuario)
    }

    override fun onPause () {
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

    private fun cargarDatos(usuario: User){
        if (usuario.FuerzaRetencion) {
            txtIRPFMan.setEnabled(true)
            cmbSituacion.setEnabled(false)
            txtGradoDis.setEnabled(false)
            txtDescend.setEnabled(false)
            txtDescMen3.setEnabled(false)
            ckbRedViv.setChecked(false)
            ckbRedViv.setEnabled(false)
        }else{
            txtIRPFMan.setEnabled(false)
            txtIRPFMan.setEnabled(false)
            cmbSituacion.setEnabled(true)
            txtGradoDis.setEnabled(true)
            txtDescend.setEnabled(true)
            txtDescMen3.setEnabled(true)
            ckbRedViv.setEnabled(true)
        }
        txtHoras.setText(usuario.Horas)
        cmbSituacion.setSelection(usuario.SituacionFamiliar)
        txtGradoDis.setText(usuario.GradoDiscapacidad)
        txtDescMen3.setText(usuario.NumDescendientesMenores3)
        txtDescend.setText(usuario.NumDescendientesMayores3)
        ckbRedViv.setChecked(usuario.ReduccionVivienda)
    }
}

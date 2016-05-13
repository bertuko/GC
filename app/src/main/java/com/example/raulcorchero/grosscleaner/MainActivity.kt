package com.example.raulcorchero.grosscleaner

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var u = Utilities(this.getBaseContext())
        if (!u.ExistsUserdata()){
            val intent = Intent(this@MainActivity, Setting::class.java)
            //startActivity(intent)
        }

    }

    override fun onStop() {
        var u = Utilities(this.getBaseContext())
        var usuario: User = u.LoadUserdata()

        //Recuperamos datos de la pantalla al usuario
        usuario.ImporteBruto = initalizeValue(this.ImporteBruto.getText().toString()).toFloat()
        usuario.NumPagas = initalizeValue(this.NumPagas.getText().toString()).toInt()

        u.saveUserdata(usuario)
    }

    private fun MostrarCamposResultado(mostrar: Boolean) {
        var stat: Int = View.INVISIBLE
        if (mostrar) { stat = View.VISIBLE }
        lblNeto.visibility = stat
        lblExtra.visibility = stat
        txtNeto.visibility = stat
        txtExtra.visibility = stat
    }

    fun calcular (v: View){
        var u = Utilities(v.context)
        var usuario: User = u.LoadUserdata()

        //Recuperamos datos de la pantalla al usuario
        usuario.ImporteBruto = initalizeValue(this.ImporteBruto.getText().toString()).toFloat()
        usuario.NumPagas = initalizeValue(this.NumPagas.getText().toString()).toInt()

        u.saveUserdata(usuario)

        if ((usuario.ImporteBruto != 0f ) && (usuario.NumPagas != 0)){
            // Llamar al calculo
            ShowDataInActivity ( (Calculation(usuario)).Calculate(), usuario )
        } else {
            var msg: String = ""
            if (usuario.ImporteBruto != 0f ) {
                msg = "Debe introducir un Salario Bruto"
            } else if (usuario.NumPagas < 12){
                msg = "El número de pagas debe ser superior a 12"
            }
            showAlert("Atención!", msg)
        }

    }

    private fun ShowDataInActivity (oDetails: Detail, oUser: User) {
        this.ImporteBruto.setText( oUser.ImporteBruto.toString() )
        this.NumPagas.setText ( oUser.NumPagas.toString() )
        this.txtNeto.setText( oDetails.PagaMensual.toString() )
        this.txtExtra.setText( oDetails.PagaExtra.toString() )
    }

    fun initalizeValue (datastring: String): String{
        var r: String = datastring.trim()
        if (r == "") {r = "0"}
        return r
    }

    fun showAlert (title: String, message: String){
        alert(message, title) {
            positiveButton("Aceptar") { }
        }.show()
    }

    fun viewSettings(v: View){
        val intent = Intent(this@MainActivity, Setting::class.java)
        startActivity(intent)
    }

}

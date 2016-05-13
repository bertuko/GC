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
    }

    fun calcular (v: View){
        var u = Utilities(v.context)
        var usuario: User = u.LoadUserdata()

        usuario.ImporteBruto = (if ( this.ImporteBruto.getText().toString().trim() == "" ) "0" else this.ImporteBruto.getText().toString()).toFloat()
        usuario.NumPagas = (if ( this.NumPagas.getText().toString().trim() == "" ) "0" else this.NumPagas.getText().toString()).toInt()
        u.saveUserdata(usuario)

        if ((usuario.ImporteBruto != 0f ) && (usuario.NumPagas != 0)){
            // Llamar al calculo
            val c: Calculation = Calculation()
            c.Calculate()
        } else {
            //mensaje de aviso... Hay que introducir importes o si no...
            alert("Oye", "¿De que vas?") {
                positiveButton("Yes") { }
                negativeButton("No") { }
            }.show()
        }

    }

    fun viewSettings(v: View){
        val intent = Intent(this@MainActivity, Setting::class.java)
        startActivity(intent)
    }

}

package com.example.raulcorchero.grosscleaner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcular (v: View){
        txtNeto.setText("prueba")
        var usuario: User = User()
        usuario.ImporteBruto = 10000f
        var u = Utilities(v.context)
        u.saveUserdata(usuario)

        var usuario2: User = u.LoadUserdata()
    }
}

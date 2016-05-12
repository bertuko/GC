package com.example.raulcorchero.grosscleaner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcular (v: View) {
        var u = Utilities(v.context)

        u.GetConfiguration()
    }

    fun btnDetalle (v: View) {
        val i = Intent(this@MainActivity, Setting::class.java)
        startActivity(i)
    }
}

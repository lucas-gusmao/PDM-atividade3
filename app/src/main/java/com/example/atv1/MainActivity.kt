package com.example.atv1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var colors: LinearLayout
    private lateinit var tvColor: TextView
    private lateinit var btnnovacor: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnnovacor = findViewById(R.id.btnnovacor)
        this.colors = findViewById(R.id.llcores)
        this.tvColor = findViewById(R.id.tviewcoresmain)

        val novaCorResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val cores = it.data?.getSerializableExtra("CORES") as Cores
                this.colors.setBackgroundColor(Color.rgb(cores.red, cores.green, cores.blue))
                this.tvColor.text = (cores.red.toString() + cores.green.toString() + cores.blue.toString())
            }else{
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
            }
        }

        this.btnnovacor.setOnClickListener {
            val intent  = Intent(this, NovaCorActivity2::class.java)
            novaCorResult.launch(intent)
        }
    }

}
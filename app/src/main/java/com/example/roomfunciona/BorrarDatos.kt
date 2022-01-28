package com.example.roomfunciona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.databinding.ActivityBorrarDatosBinding
import kotlinx.coroutines.launch

class BorrarDatos : AppCompatActivity() {
    lateinit var binding:ActivityBorrarDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var error = false;
        binding.btBorrar.setOnClickListener {
            if (binding.etId.text.isNotBlank()) {
                lifecycleScope.launch {
                    val amigo = RoomApp.db.misAmigosDao().getPorId(binding.etId.text.toString().toInt())
                    if(amigo != null) {
                        RoomApp.db.misAmigosDao().delete(amigo)
                    }else{
                       error = true
                    }
                }
            }else{
                error = true
            }
            if(error){
                Toast.makeText(this,"No se ha podido borrar",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
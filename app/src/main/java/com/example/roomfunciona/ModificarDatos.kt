package com.example.roomfunciona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.databinding.ActivityBorrarDatosBinding
import com.example.roomfunciona.databinding.ActivityModificarDatosBinding
import kotlinx.coroutines.launch

class ModificarDatos : AppCompatActivity() {
    lateinit var binding: ActivityModificarDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var error = false;
        binding.btModificar.setOnClickListener {
            if (binding.etId.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank() &&
                binding.etId.text.isNotBlank()
            ) {

                lifecycleScope.launch {
                    var amigo =
                        RoomApp.db.misAmigosDao().getPorId(binding.etId.text.toString().toInt())
                    if (amigo != null) {
                        amigo.email = binding.etEmail.text.toString()
                        amigo.nombre = binding.etNombre.text.toString()
                        RoomApp.db.misAmigosDao().update(amigo)
                    } else {
                        error = true
                    }
                }
            } else {
                error = true
            }
            if (error) {
                Toast.makeText(
                    this,
                    "No se ha podido modificar",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
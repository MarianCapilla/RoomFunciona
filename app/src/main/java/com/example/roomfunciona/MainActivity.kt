package com.example.roomfunciona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.roomfunciona.RoomApp.Companion.db
import com.example.roomfunciona.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etEmail.text.isNotBlank()) {
                    addAmigo(MisAmigos(nombre = binding.etNombre.text.toString(), email = binding.etEmail.text.toString()))
            }

        }
        binding.btConsultar.setOnClickListener {
            lifecycleScope.launch {
                val todos = db.misAmigosDao().getTodo()
                var i = 0;
                var guardado = "";
                while(i < todos.size){
                  guardado  +=  todos[i].id.toString()+": "+ todos[i].nombre +" "+todos[i].email+" \n"
                    i++
                }
                binding.tvConsulta.text = guardado
            }
        }
        binding.btIrABorrar.setOnClickListener{
            val intent = Intent(this, BorrarDatos::class.java)
            startActivity(intent)
        }
        binding.btIrAModificar.setOnClickListener{
            val intent = Intent(this, ModificarDatos::class.java)
            startActivity(intent)
        }

    }

    private fun addAmigo(miAmigo: MisAmigos) {
        lifecycleScope.launch {
            db.misAmigosDao().insertar(miAmigo)
        }
    }
}
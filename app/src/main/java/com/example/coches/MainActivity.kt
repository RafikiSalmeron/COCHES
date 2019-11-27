package com.example.coches

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    companion object{
        var respuesta =" ERROR "
    }

    override fun onResume() {
        super.onResume()
        recargar()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       recargar()
    }

    fun recargar(){
        val recyclerView:RecyclerView=findViewById(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL, false)

        var concesionario = ArrayList<Coche>()
        concesionario.clear()
        cargarCoches()

        var coches = URLJsonObjeto()
        if (coches != null) {
            concesionario = coches.get()!!
            println(concesionario.get(0).imgcoche + " IMAGEN " + concesionario.get(0).marca)
        }else{
            println("Hola")
        }


        //coches.add(Coche("0000ABC", "Nissan", "Micra", "Diesel", 8000.00, R.drawable.error))
        //coches.add(Coche("0000BBB", "Opel", "CORSA", "Gasolina", 7000.00, R.drawable.error))
        //coches.add(Coche("0000CCC", "Mini", "Cooper", "Gasolina", 10000.00, R.drawable.error))


        val adapter=AdaptadorCoches(concesionario)
        recyclerView.adapter=adapter

    }


    fun URLJsonObjeto(): CocheArray? {

        val gson = Gson()
        try {


            val json = respuesta

            println(json + " RESPUESTA")
            val coche = gson.fromJson(json, CocheArray::class.java)
            return coche
            }
        catch (e: Exception){
            Log.d("RESULTADO", "error")
            return null
        }
    }

    fun cargarCoches(){
        MyAsinc().execute()
        Thread.sleep(1000)
    }
    fun newActividad(view : View){
        val intento = Intent(this,insertar::class.java)
        startActivity(intento)
    }
}
class MyAsinc : AsyncTask<String, String, String>(){
    override fun doInBackground(vararg p0: String?): String {

        MainActivity.respuesta = URL("http://iesayala.ddns.net/Rafiki/json01.php").readText()
        println(MainActivity.respuesta)
        return "OK"
    }
}
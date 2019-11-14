package com.example.coches

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView:RecyclerView=findViewById(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL, false)
        //val coches = ArrayList<Coche>()
        var concesionario = ArrayList<Coche>()

        var coches = URLJsonObjeto()
        if (coches != null) {
            concesionario = coches.get()!!
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


            val json = leerUrl("http://iesayala.ddns.net/Rafiki/json01.php")

            val coche = gson.fromJson(json, CocheArray::class.java)
            return coche


            }
        catch (e: Exception){
            Log.d("RESULTADO", "error")
            return null
        }

    }
    private fun leerUrl(urlString:String): String{

        val response = try {
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }
        Log.d("error", response)
        return response
    }


    fun leerFichero(fichero: String): String {
        var stringFichero=""
        try {
            val stream = assets.open(fichero)
            val tamano = stream.available()
            val buffer = ByteArray(tamano)
            stream.read(buffer)
            stream.close()
            stringFichero= String(buffer)

        }
        catch (e: IOException){

        }
        return stringFichero

    }
}

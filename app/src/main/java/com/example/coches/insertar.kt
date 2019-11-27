package com.example.coches

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.get
import java.net.URL

class insertar : AppCompatActivity() {

    companion object{
        var url =""
    }
    var marca = ""
    var imagen = "http://iesayala.ddns.net/Rafiki/img/"
    var modelo = ""
    var precio = 0.0
    var precio1 = ""
    var combustible = ""
    var matricula = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)




        var option : Spinner = findViewById(R.id.combobox)






        var opciones = arrayOf("CHEVROLET", "FORD", "NISSAN", "OPEL", "OTRO")

        option.adapter = ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, opciones)


        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                marca = opciones.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }




    }
fun Insertar(view : View){
    if(marca.equals("CHEVROLET")){imagen = imagen+ "chevrolet.jpg"}
    if(marca.equals("FORD")){imagen = imagen+ "ford.jpg"}
    if(marca.equals("OPEL")){imagen = imagen+ "opel.png"}
    if(marca.equals("NISSAN")){imagen = imagen+ "nissan.jpg"}
    if(marca.equals("OTRO")){imagen = imagen+ "interrogacion.png"}


    var modeloET : EditText = findViewById(R.id.etModelo)
    var matriculaET :EditText = findViewById(R.id.etMatricula)
    var combustibleET : EditText = findViewById(R.id.etCombustible)
    var precioET : EditText = findViewById(R.id.etPrecio)

    modelo = modeloET.text.toString()
    matricula = matriculaET.text.toString()
    combustible = combustibleET.text.toString()
    precio1 = precioET.text.toString()
    precio = precio1.toDouble()

     url = "http://iesayala.ddns.net/Rafiki/insertarcoche.php/?matricula="+matricula + "&marca=" +marca+ "&combustible="+combustible+"&modelo="+modelo+"&imagen="+imagen+"&precio="+precio

    println(url)

    MyAsincTask().execute()


    finish()



}



}

class MyAsincTask : AsyncTask<String, String, String>(){
    override fun doInBackground(vararg p0: String?): String {

        URL(insertar.url).readText()
        return "OK"
    }
}


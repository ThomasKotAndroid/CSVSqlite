package com.example.sqlitereadcsv


import android.Manifest
import android.annotation.SuppressLint

import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.sqlitereadcsv.DBHelper.Companion.address
import com.example.sqlitereadcsv.DBHelper.Companion.first_name
import com.example.sqlitereadcsv.DBHelper.Companion.last_name
import java.io.File
import java.io.InputStream
import java.lang.Exception
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(folder, "100-contacts.csv")
        val db = DBHelper(this, null)
        val inputStream: InputStream = file.inputStream()
        val lines = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine { lines.add(it) }
        //header has column names so remove it
        lines.removeAt(0)
        lines.forEach{ db.addLine(it) }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val editText: EditText = findViewById(R.id.editText)
            try {
                val num: String = editText.text.toString()
                if(checkFields(num.toInt())){
                //if (num.toInt() in 1..99) {
                    val textView: TextView = findViewById(R.id.textView)
                    val cursor = db.getLine(num)
                    cursor!!.moveToFirst()
                    textView.setText(CursorToString.returnString(cursor))


                } else {
                    Toast.makeText(this, "Enter number between 1 and 99", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e("AndroidKotlin App Error",e.toString())
            }
        }

    }


//not returning true ever..need to check regex
    private fun checkFields(number:Int) :Boolean{

    val regex = Regex("\\b([1-9]|[1-9][0-9])\\b")

           return regex.matches(number.toString())
        }
}
package com.example.sqlitereadcsv

import android.annotation.SuppressLint
import android.database.Cursor
import com.example.sqlitereadcsv.DBHelper.Companion.address
import com.example.sqlitereadcsv.DBHelper.Companion.comp_name
import com.example.sqlitereadcsv.DBHelper.Companion.first_name
import com.example.sqlitereadcsv.DBHelper.Companion.last_name

class CursorToString {

    companion object{
        @SuppressLint("Range")
        fun  returnString(cursor: Cursor):String{



            val a = "Name : " + (cursor.getString(cursor.getColumnIndex(first_name))) + " " + (cursor.getString(cursor.getColumnIndex(last_name )))


            val b = "Company : " + (cursor.getString(cursor.getColumnIndex(comp_name)))
            val c = "Address : " + (cursor.getString(cursor.getColumnIndex(address)))


        return "$a $b $c"
        }
    }
}
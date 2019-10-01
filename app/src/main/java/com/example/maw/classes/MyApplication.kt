package com.example.maw.classes

import android.app.Application
import android.content.Context
import com.example.pacermirror.database.DBHelper

class MyApplication : Application() {


    companion object{

        lateinit var context : Context
        //initialize db instance
        var db: DBHelper? = null
    }
    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        //check db instance already created else create db object
        //create db object
        if(db == null)
        {
            db = DBHelper(context)
            db!!.writableDatabase
        }

    }
}
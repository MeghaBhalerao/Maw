package com.example.maw.classes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class T
{
    companion object{


        //toast
        fun t(message : String)
        {
            Toast.makeText(MyApplication.context,message, Toast.LENGTH_LONG).show()
        }
        fun e(message: String)
        {
            Log.e("HOT_MIX_PLANT_LOG",message)
        }
        //check network
        fun isNetworkAvailable(): Boolean
        {
            val connectivityManager = MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        //validate data
        fun editextEmpty(editext : EditText, message : String) : Boolean
        {

            if(editext.text.toString().isEmpty())
            {
                t(message)
                return false
            }
            else
            {
                return true
            }
        }

    }
}
package com.example.ownottapp.util

import android.content.Context
import android.content.SharedPreferences
import com.example.ownottapp.model.Configuration
import com.example.ownottsampleapp.R
import com.google.gson.Gson


class AppSessionManager(var context: Context) {
    companion object{
        var sp:SharedPreferences?=null
    }

    fun saveConfiguration(model: Configuration?){
        if (sp==null){
            sp=context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        }
        val editor=sp?.edit()
        editor?.putString("configuration",Gson().toJson(model))
        editor?.apply()
    }

    fun fetchConfiguration():Configuration?{
        if (sp==null){
            sp=context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        }
        val configString=sp?.getString("configuration","")
        if (configString!="") {
            val configObject=Gson().fromJson(configString,Configuration::class.java)
            return configObject
        }
        return  null
    }
}
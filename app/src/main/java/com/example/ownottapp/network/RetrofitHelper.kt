package com.example.ownottapp.network

import com.example.ownottapp.util.StringConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {


//    val baseUrl = "https://quotable.io/"
//
//    fun getInstance(): Retrofit {
//        return Retrofit.Builder().baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            // we need to add converter factory to
//            // convert JSON object to Java object
//            .build()
//    }

    fun getInstance(java: Class<INetworkAPI>):INetworkAPI{
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(StringConstants.BASE_URL)
            .build()
        return retrofit.create(INetworkAPI::class.java)
    }


}
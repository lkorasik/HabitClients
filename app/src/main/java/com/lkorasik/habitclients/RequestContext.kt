package com.lkorasik.habitclients

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestContext {
    // deploy: http://195.133.197.167:8080/
    private const val BASE_URL = "http://192.168.0.231:8080"

    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val client = OkHttpClient()
        .newBuilder()
//        .addInterceptor(AuthInterceptor())
        .build()
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    private val doubleHabitsAPI: HabitsTrackerAPI = retrofit.create(HabitsTrackerAPI::class.java)

    val API: HabitsTrackerAPI
        get() = doubleHabitsAPI
}

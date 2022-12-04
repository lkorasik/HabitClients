package com.lkorasik.habitclients

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface HabitsTrackerAPI {
    @POST("/auth/registration")
    fun registration(@Body registration: RegistrationDTO): Call<ResponseBody>

    @POST("/auth/login")
    fun login(@Body login: LoginDTO): Call<Void?>

    @GET("/habit_list/get_habitList")
    fun getHabitList(@Header(HeadersKeys.AUTHORIZATION) token: String): Call<HabitListDTO>

    @POST("/habit/create_habit")
    fun createHabit(@Header(HeadersKeys.AUTHORIZATION) token: String, @Body habit: HabitDTO): Call<Void?>
}
package com.lkorasik.habitclients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HabitsListActivity : AppCompatActivity() {
    private lateinit var list: ListView
    private lateinit var fab: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        fab = findViewById(R.id.add_habit)
        fab.setOnClickListener {
            val myIntent = Intent(this@HabitsListActivity, AddHabit::class.java)
//                myIntent.putExtra("key", value) //Optional parameters

            this@HabitsListActivity.startActivity(myIntent)
        }

        list = findViewById(R.id.habits_list)
        val adapter = HabitRecycleViewAdapter(emptyList(), this)
        list.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("Test", "Start")
            val result = RequestContext.API.getHabitList(JwtRepository.token!!)
                .enqueue(object : Callback<HabitListDTO> {
                    override fun onResponse(
                        call: Call<HabitListDTO>?,
                        response: Response<HabitListDTO>?,
                    ) {
                        val adapter = HabitRecycleViewAdapter(response!!.body().habits,
                            this@HabitsListActivity)
                        list.adapter = adapter
                    }

                    override fun onFailure(call: Call<HabitListDTO>?, t: Throwable?) {
                    }
                })

            Log.i("Got", "Got $result")
        }
    }

    override fun onResume() {
        super.onResume()

        val handler = Handler()
        handler.postDelayed({
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("Test", "Start")
                val result = RequestContext.API.getHabitList(JwtRepository.token!!)
                    .enqueue(object : Callback<HabitListDTO> {
                        override fun onResponse(
                            call: Call<HabitListDTO>?,
                            response: Response<HabitListDTO>?,
                        ) {
                            val adapter = HabitRecycleViewAdapter(response!!.body().habits,
                                this@HabitsListActivity)
                            list.adapter = adapter
                        }

                        override fun onFailure(call: Call<HabitListDTO>?, t: Throwable?) {
                        }
                    })

                Log.i("Got", "Got $result")
            }
        }, 500)


    }
}
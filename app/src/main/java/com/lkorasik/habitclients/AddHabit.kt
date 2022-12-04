package com.lkorasik.habitclients

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddHabit : AppCompatActivity() {
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var count: EditText
    private lateinit var periodicity: EditText
    private lateinit var save: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_view_habit)

        title = findViewById(R.id.habit_name)
        description = findViewById(R.id.habit_description)
        count = findViewById(R.id.count)
        periodicity = findViewById(R.id.periodicity)
        save = findViewById(R.id.create_habit)

        save.setOnClickListener {
            val dto = HabitDTO(
                title = title.text.toString(),
                description = description.text.toString(),
                priority = Priority.HIGH,
                color = Color.GREEN,
                repeats = periodicity.text.toString().toLong(),
                done_dates = emptyList()
            )

            CoroutineScope(Dispatchers.IO).launch {
                Log.i("Test", "Start")
                val result = RequestContext.API.createHabit(JwtRepository.token!!, dto).execute()
                Log.i("Got", "Got $result")
            }

            finish()
        }
    }
}
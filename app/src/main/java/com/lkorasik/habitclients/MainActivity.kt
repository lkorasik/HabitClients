package com.lkorasik.habitclients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var habitListName: EditText
    private lateinit var registrate: Button
    private lateinit var login: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        habitListName = findViewById(R.id.habit_list_name)
        registrate = findViewById(R.id.register)
        login = findViewById(R.id.login)

        registrate.setOnClickListener {
            val dto = RegistrationDTO(
                username = username.text.toString(),
                password = password.text.toString(),
                habitListName = habitListName.text.toString()
            )

            CoroutineScope(Dispatchers.IO).launch {
                Log.i("Test", "Start")
                val result = RequestContext.API.registration(dto).execute()
                Log.i("Test", "End $result")

//                Toast.makeText(this@MainActivity, "Go to login", Toast.LENGTH_LONG).show()
            }
        }

        login.setOnClickListener {
            val dto = LoginDTO(
                username = username.text.toString(),
                password = password.text.toString(),
            )

            CoroutineScope(Dispatchers.IO).launch {
                Log.i("Test", "Start")
                val result = RequestContext.API.login(dto).execute()

                val token = result.headers()[HeadersKeys.AUTHORIZATION].toString().split(' ')[1]
                JwtRepository.token = token

                val myIntent = Intent(this@MainActivity, HabitsListActivity::class.java)
//                myIntent.putExtra("key", value) //Optional parameters

                this@MainActivity.startActivity(myIntent)
            }
        }
    }
}
package com.example.mobilt_java22_shkelqim_gashi_apiintegration_v3

import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onResume() {
        super.onResume()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val dogImageView = findViewById<ImageView>(R.id.dogImageView)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(DogAPI::class.java)

        submitButton.setOnClickListener {
            api.getRandomDogImage().enqueue(object : Callback<DogResponse> {
                override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                    val dogData = response.body()
                    Picasso.get().load(dogData?.message).into(dogImageView)
                }

                override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error fetching dog image",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.frag1) {
            moveTaskToBack(true)
        } else {
            super.onBackPressed()
        }
    }
}

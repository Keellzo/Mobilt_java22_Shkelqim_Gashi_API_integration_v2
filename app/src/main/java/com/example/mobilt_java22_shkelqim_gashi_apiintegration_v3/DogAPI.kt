package com.example.mobilt_java22_shkelqim_gashi_apiintegration_v3

import retrofit2.Call
import retrofit2.http.GET

interface DogAPI {
    @GET("breeds/image/random")
    fun getRandomDogImage(): Call<DogResponse>

}

package com.example.retrofitbasics.feature.login.repository

import com.example.retrofitbasics.feature.login.model.ForgotPasswordUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordAPI {

    @POST("forgot-password")
    fun forgotPassword(@Body forgotPasswordUser: ForgotPasswordUser): Call<ForgotPasswordUser>
}
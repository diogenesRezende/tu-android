package com.example.retrofitbasics.feature.login.model

import com.google.gson.annotations.SerializedName


data class ForgotPasswordUser(val id: Int? = null, @SerializedName("email") val email: String)
package com.example.retrofitbasics.feature.login.repository

import com.example.retrofitbasics.core.config.ApiFactory
import com.example.retrofitbasics.feature.login.model.ForgotPasswordUser
import retrofit2.Call

class ForgotPasswordClient {

     private var api: ForgotPasswordAPI = ApiFactory().create().create(ForgotPasswordAPI::class.java)

    fun forgotPassword(forgotPasswordUser: ForgotPasswordUser): Call<ForgotPasswordUser> {
        return api.forgotPassword(forgotPasswordUser)
    }
}
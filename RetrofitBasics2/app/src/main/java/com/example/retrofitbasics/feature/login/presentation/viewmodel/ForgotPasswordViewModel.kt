package com.example.retrofitbasics.feature.login.presentation.viewmodel

import com.example.retrofitbasics.feature.login.model.ForgotPasswordUser
import com.example.retrofitbasics.feature.login.repository.ForgotPasswordClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel {
    private var client = ForgotPasswordClient()

    fun forgotPassword(
        email: String,
        onSuccess: (ForgotPasswordUser) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        client.forgotPassword(ForgotPasswordUser(1 , email))
            .enqueue(object : Callback<ForgotPasswordUser> {
                override fun onResponse(
                    call: Call<ForgotPasswordUser>,
                    response: Response<ForgotPasswordUser>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let(onSuccess)
                    } else {
                        onError(Throwable(""))
                    }
                }

                override fun onFailure(call: Call<ForgotPasswordUser>, t: Throwable) {
                    onError(t)
                }
            })
    }

}
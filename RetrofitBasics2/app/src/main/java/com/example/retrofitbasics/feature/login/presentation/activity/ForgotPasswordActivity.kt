package com.example.retrofitbasics.feature.login.presentation.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.retrofitbasics.R
import com.example.retrofitbasics.feature.login.presentation.viewmodel.ForgotPasswordViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var contextView: View

    lateinit var ilEmail: TextInputLayout
    lateinit var etEmail: EditText
    private lateinit var btForgotPassword: Button

    lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        etEmail = findViewById(R.id.etEmail)
        ilEmail = findViewById(R.id.ilEmail)
        btForgotPassword = findViewById(R.id.btForgotPassword)

        btForgotPassword.setOnClickListener { forgotPassword() }

        contextView = findViewById(android.R.id.content)
        viewModel = ForgotPasswordViewModel()
    }

    override fun onResume() {
        super.onResume()

//        Glide.with(this).load("http://goo.gl/gEgYUd")
//            .listener(object : RequestListener<Drawable>{
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("glideApp", "Não carregou parça")
//
//                    return true
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    Log.d("glideApp", "Carregou parça")
//                    return false
//                }
//
//            }).into(ivImage)
    }

    private fun forgotPassword() {
        ilEmail.isErrorEnabled = false
        val email = etEmail.text.toString()

        if (email.isEmpty()) {
            ilEmail.error = ""
            ilEmail.isErrorEnabled = true
            Snackbar.make(contextView, "É necessário preencher o email", Snackbar.LENGTH_SHORT)
                .show()
            return
        }

        viewModel.forgotPassword(email, {
            Snackbar.make(contextView, "Success", Snackbar.LENGTH_SHORT).show()
            finish()
        }, {
            Snackbar.make(contextView, "Failure", Snackbar.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, ForgotPasswordActivity::class.java)
    }
}
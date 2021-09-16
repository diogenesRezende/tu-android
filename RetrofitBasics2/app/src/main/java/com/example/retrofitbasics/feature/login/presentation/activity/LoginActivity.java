package com.example.retrofitbasics.feature.login.presentation.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitbasics.R;
import com.example.retrofitbasics.feature.login.model.User;
import com.example.retrofitbasics.feature.login.presentation.viewmodel.LoginViewModel;
import com.example.retrofitbasics.feature.login.repository.LoginCallBack;
import com.example.retrofitbasics.feature.todo.presentation.activity.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private View contextView;
    private Context context;

    private TextInputLayout ilEmail;
    private EditText etEmail;
    private TextInputLayout ilPassword;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvForgotPassword;
    private View loading;
    private ImageView ivImage;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new LoginViewModel();

        etEmail = findViewById(R.id.etEmail);
        ilEmail = findViewById(R.id.ilEmail);
        etPassword = findViewById(R.id.etPassword);
        ilPassword = findViewById(R.id.ilPassword);
        btLogin = findViewById(R.id.btLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        loading = findViewById(R.id.loading);
        ivImage = findViewById(R.id.ivImage);
        btLogin.setOnClickListener(view -> login());

        contextView = findViewById(android.R.id.content);
        context = this;

        tvForgotPassword.setOnClickListener(view -> startActivity(ForgotPasswordActivity.Companion.newInstance(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        String user_pref = sharedPref.getString("user_pref", null);
//        if (user_pref != null && !user_pref.isEmpty()) {
//            startActivity(MainActivity.newInstance(context));
//            finish();
//        }

    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        ilEmail.setErrorEnabled(false);
        ilPassword.setErrorEnabled(false);

        if (email.isEmpty()) {
            Snackbar.make(contextView, "É necessário preencher o email", Snackbar.LENGTH_SHORT).show();
            ilEmail.setError("");
            ilEmail.setErrorEnabled(true);
            return;
        }
        if (password.isEmpty()) {
            Snackbar.make(contextView, "É necessário preencher a senha", Snackbar.LENGTH_SHORT).show();
            ilPassword.setError("");
            ilPassword.setErrorEnabled(true);
            return;
        }

        loading.setVisibility(View.VISIBLE);
        viewModel.login(email, password, new LoginCallBack() {
            @Override
            public void onSuccess(User user) {
                loading.setVisibility(View.GONE);
                SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String userPref = new Gson().toJson(user);
                sharedPref.edit().putString("user_pref", userPref).apply();
                startActivity(MainActivity.newInstance(context));
                finish();
            }

            @Override
            public void onError(Throwable error) {
                loading.setVisibility(View.GONE);
                Snackbar.make(contextView, "Não Rolou parça", Snackbar.LENGTH_SHORT).show();
            }
        });

    }


}
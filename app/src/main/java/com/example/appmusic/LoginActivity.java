package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmusic.model.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        initView();
        initAction();


    }

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    LoginPresenter loginPresenter;

    public void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
    public void initAction() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            loginPresenter.login(username,password);
        }
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "Login Fail!!",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccessful() {
        Toast.makeText(this, "Login Thanh Cong!!",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void navigateHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setErrorUsername() {
        edtUsername.setError("Vui long nhap Username");
    }

    @Override
    public void setErrorPassword() {
        edtPassword.setError("Vui long nhap Password");
    }
}

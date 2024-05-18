package com.example.zman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zman.UserHelper.UserOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnSignIn;
    EditText etEmail, etPassword;
    UserOpenHelper uh1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uh1 = new UserOpenHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnSignIn = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSignIn) {
            Intent intent = new Intent(this, SignInPage.class);
            startActivity(intent);
        }

        if(view == btnLogin) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }
}
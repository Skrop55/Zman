package com.example.zman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Preference extends AppCompatActivity implements View.OnClickListener {
    Button btnSend, btnBack;

    EditText etTime;

    public static int i = 0;

    public static int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    SharedPreferences SharedPreferences;
    public static final String SHARED_PREF = "myPref";
    private static final String KEY_MINUTES = "minutes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        etTime = findViewById(R.id.etTime);

        SharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String minutes = SharedPreferences.getString(KEY_MINUTES, null);
        if(minutes != null) {
            Intent intent = new Intent(this,PomodoroTimer.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        if(view == btnBack){
            SharedPreferences.Editor editor = SharedPreferences.edit();
            editor.clear();
            editor.commit();
            i = 0;
            finish();
            Toast.makeText(this, "Cleared changes", Toast.LENGTH_LONG).show();

        }
        if(view == btnSend){
            SharedPreferences.Editor editor = SharedPreferences.edit();
            editor.putString(KEY_MINUTES, etTime.getText().toString());
            editor.apply();
            Intent intent = new Intent(this,PomodoroTimer.class);
            startActivity(intent);

            Toast.makeText(this, "changes applied!", Toast.LENGTH_LONG).show();
        }
    }
}
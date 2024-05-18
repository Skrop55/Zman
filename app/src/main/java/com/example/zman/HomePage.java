package com.example.zman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zman.Backend.Globals;
import com.example.zman.SkillHelper.AllSkills;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity implements View.OnClickListener, NavigationBarView.OnItemSelectedListener {
    Button btnSkill, btnVibrate, pomodoro, preference, exit;
    BottomNavigationView nav;
    Vibrator vbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        preference = findViewById(R.id.btnPreference);
        preference.setOnClickListener(this);

        pomodoro = findViewById(R.id.pomodoro);
        pomodoro.setOnClickListener(this);

        btnSkill = findViewById(R.id.btnskill1);
        btnSkill.setOnClickListener(this);

        exit = findViewById(R.id.btnLogOut);
        exit.setOnClickListener(this);

        btnVibrate = findViewById(R.id.btnVibrate);
        btnVibrate.setOnClickListener(this);

        nav = findViewById(R.id.bottNav);
        nav.setSelectedItemId(R.id.bottNav);
        nav.setOnItemSelectedListener(this);

        vbr = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }
    public void vibrateClick() {
        Toast.makeText(this, "vibrate", Toast.LENGTH_LONG).show();
        vbr.vibrate(1000); //there's a permission in manifest
    }
    public void notification() {
        String notiTitle="This is a notification for you";
        String notiText="Click here to open our about page";
        Intent intent=new Intent(this, About.class);
        Globals.makeNotification(this, intent, notiTitle, notiText);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSkill) {
            Intent intent = new Intent(this, AllSkills.class);
            startActivity(intent);
        } else if(view == btnVibrate) {
            vibrateClick();
            notification();
        }

        if(view == pomodoro) {
            Intent intent = new Intent(this,PomodoroTimer.class);
            startActivity(intent);
        }

        if(view == exit)
            finish();

        if(view == preference) {
            Intent intent = new Intent(this,Preference.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.tasks) {
            Intent intent = new Intent(this, Tasks.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.calendar) {
            Uri uri = Uri.parse("https://calendar.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        return false;
    }
}
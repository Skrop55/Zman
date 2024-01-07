package com.example.zman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zman.SkillHelper.AllSkills;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TaskList extends AppCompatActivity implements View.OnClickListener, NavigationBarView.OnItemSelectedListener {
    Button btnSkill;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        btnSkill = findViewById(R.id.btnSkill1);
        btnSkill.setOnClickListener(this);

        nav = findViewById(R.id.bottNav);
        nav.setSelectedItemId(R.id.bottNav);
        nav.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == view) {
            Intent intent = new Intent(this, AllSkills.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.tasks) {
            Intent intent = new Intent(this, TaskList.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.tasks) {
            Intent intent = new Intent(this, TaskList.class);
            startActivity(intent);
        }
        return false;
    }
}
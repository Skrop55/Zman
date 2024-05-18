package com.example.zman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity implements View.OnClickListener, NavigationBarView.OnItemSelectedListener {
    private ArrayList<String> items;
    private ListView list;
    private Button button;
    private ArrayAdapter<String> itemsAdapter;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        list = findViewById(R.id.list);
        button = findViewById(R.id.addButton);
        button.setOnClickListener(this);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(getApplicationContext(), "Item Removed", Toast.LENGTH_LONG).show();
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
            }
        });

        nav = findViewById(R.id.bottNav);
        nav.setSelectedItemId(R.id.bottNav);
        nav.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == button) {
            additem(view);
        }
    }

    private void additem(View view) {
        EditText input = findViewById(R.id.editText);
        String itemText = input.getText().toString();
        if(!itemText.equals("")) {
            itemsAdapter.add(itemText);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Please Enter Text..", Toast.LENGTH_LONG).show();
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
        else if(item.getItemId() == R.id.tasks) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
        return false;
    }
}
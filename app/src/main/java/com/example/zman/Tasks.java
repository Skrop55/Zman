package com.example.zman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> items;
    private ListView list;
    private Button button;
    private ArrayAdapter<String> itemsAdapter;

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
                remove(position);
            }
        });
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

    private void remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(getApplicationContext(),
                "Item Removed", Toast.LENGTH_LONG).show();
    }
}
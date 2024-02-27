package com.example.zman.UserHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zman.R;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    UserOpenHelper uoh;
    ArrayList<User> listOfUsers;
    ListView listView;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        listView = findViewById(R.id.listAllUsers);
        uoh = new UserOpenHelper(this);

        listOfUsers = new ArrayList();
        uoh.open();
        listOfUsers = uoh.getAllUsers();
        uoh.close();

        userAdapter = new UserAdapter(this, 0, listOfUsers);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) { //going to update user screen
        User u = userAdapter.getItem(i);
        Toast.makeText(getBaseContext(), u.getEmail() + " touched", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AllUsersActivity.this, UpdateUser.class);
        intent.putExtra("rowId", u.getId());
        startActivity(intent);
        return false;
    }
}
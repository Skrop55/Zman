package com.example.zman.UserHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zman.R;

public class UpdateUser extends AppCompatActivity implements View.OnClickListener {
    Button btnUpdate, btnDelete;
    EditText etEmail3, etPassword3;
    UserOpenHelper uh1;
    long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        id = getIntent().getLongExtra("rowId", 0);

        etEmail3 = findViewById(R.id.etEmail3);
        etPassword3 = findViewById(R.id.etPassword3);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(UpdateUser.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //disable the default title.
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.delete_dialog);

        Button submitButton = dialog.findViewById(R.id.Sure);

        submitButton.setOnClickListener(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                uh1.open();
                uh1.deleteByRow(id);
                uh1.close();
                Toast.makeText(UpdateUser.this, " user deleted! ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateUser.this, AllUsersActivity.class);
                startActivity(i);
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == btnDelete) { //delete user
            showCustomDialog();
        }

        if (view == btnUpdate) {
            String email = etEmail3.getText().toString();
            String pass = etPassword3.getText().toString();
            User u = new User(email,pass, null);
            //u.setId(id);
            uh1.open();
            uh1.updateByRow(u);
            uh1.close();
            Toast.makeText(UpdateUser.this, "user Updated! " + u.getEmail().toString(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateUser.this, AllUsersActivity.class);
            startActivity(i);
        }
    }
}

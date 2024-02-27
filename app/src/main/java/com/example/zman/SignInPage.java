package com.example.zman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zman.Backend.IsCheck;
import com.example.zman.UserHelper.AllUsersActivity;
import com.example.zman.UserHelper.User;
import com.example.zman.UserHelper.UserOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class SignInPage extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn2, btnBack2;
    EditText etEmail2, etPassword2;
    ImageButton btnImage;
    ImageView imageView;
    String msg = "";
    UserOpenHelper uh1;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        uh1 = new UserOpenHelper(this);

        etEmail2 = findViewById(R.id.etEmail2);
        etPassword2 = findViewById(R.id.etPassword2);
        btnSignIn2 = findViewById(R.id.btnSignUp2);

        btnBack2 = findViewById(R.id.btnBack2); // back
        btnBack2.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);
        btnImage = findViewById(R.id.btnImage1);
        btnImage.setOnClickListener(this);

        btnSignIn2.setOnClickListener(this); // is check implemented
    }

    @Override
    public void onClick(View view) {
        if(view == btnBack2) { // back
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        String Email = etEmail2.getText().toString(); // is check implemented
        String Password = etPassword2.getText().toString();
        byte[] image = imageViewToByte(imageView);

        if (view == btnSignIn2) {
            IsCheck str = new IsCheck(Email);
            msg = msg + str.checkEmail();

            if (msg.equals("")) {
                User u1 = new User(Email, Password, image);
                uh1.open();
                uh1.createUser(u1);
                uh1.close();
                Intent intent = new Intent(this, AllUsersActivity.class);
                startActivity(intent);
            }

            else {
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                msg = "";
            }
        }

        if(view == btnImage) {
            showPictureDialog();
        }
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Where the picture is from? ");
        String[] pictureDialogItems = {"Gallery", "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivityForResult(intent, CAMERA);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(SignInPage.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SignInPage.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(SignInPage.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }}
    public String saveImage( Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        return "";
    }
    //Convert imageView to byte[]
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        byte[]byteArray=stream.toByteArray();
        return byteArray;
    }
}

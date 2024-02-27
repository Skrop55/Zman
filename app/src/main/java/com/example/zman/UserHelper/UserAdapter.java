package com.example.zman.UserHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zman.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    Context context;
    List<User> objects;


    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.one_row, parent, false);

        TextView tvEmail = view.findViewById(R.id.tvEmail);
        TextView tvPassword = view.findViewById(R.id.tvPassword);
        ImageView img = view.findViewById(R.id.imgv);

        User temp = objects.get(position);
        tvEmail.setText(temp.getEmail());
        tvPassword.setText(temp.getPassword());
        byte[] userImage = temp.getPicture();
        if (userImage != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(userImage, 0, userImage.length);
            img.setImageBitmap(bitmap);
        }

        return view;
    }
}

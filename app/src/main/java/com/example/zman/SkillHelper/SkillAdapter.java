package com.example.zman.SkillHelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zman.R;
import com.example.zman.UserHelper.User;

import java.util.List;

public class SkillAdapter extends ArrayAdapter<Skill> {
    Context context;
    List<Skill> objects;


    public SkillAdapter(Context context, int resource, List<Skill> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.one_row, parent, false);

        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        TextView tvPassword = (TextView) view.findViewById(R.id.tvPassword);

        Skill temp = objects.get(position);
        tvEmail.setText(temp.getLevel());
        tvPassword.setText(temp.getType());

        return view;
    }
}

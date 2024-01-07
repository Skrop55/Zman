package com.example.zman.SkillHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.zman.R;
import com.example.zman.SkillHelper.Skill;
import com.example.zman.SkillHelper.SkillAdapter;
import com.example.zman.SkillHelper.SkillOpenHelper;

import java.util.ArrayList;

public class AllSkills extends AppCompatActivity {
    SkillOpenHelper soh;
    ArrayList<Skill> listOfSkills;
    ListView listView;
    SkillAdapter skillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_skills);


        listView = (ListView) findViewById(R.id.listSkills1);
        soh = new SkillOpenHelper(this);

        listOfSkills = new ArrayList<Skill>();
        soh.open();
        listOfSkills = soh.getAllSkills();
        soh.close();

        skillAdapter = new SkillAdapter(this, 0, listOfSkills);
        listView.setAdapter(skillAdapter);
    }
}
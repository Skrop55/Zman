package com.example.zman.SkillHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.zman.UserHelper.User;
import com.example.zman.UserHelper.UserOpenHelper;

import java.util.ArrayList;

public class SkillOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "DBskills1"; //שם מסד נתונים
    public static final String TABLE_SKILL = "tblSkill1"; //שם הטבלה
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "ID";//מפתח ראשי - מספור אוטומטי
    //פירוט שדות(שמות עמודות)
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_XP = "xp";

    //יצירת טבלה
    private static final String CREATE_TABLE_ALL_SKILLS = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SKILL + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +  COLUMN_TYPE + " VARCHAR, " +  COLUMN_LEVEL + " VARCHAR, "
            + COLUMN_XP + " VARCHAR " + ");";

    //מערך כולל שמות השדות(עמודות)
    String[] allColumns = {COLUMN_ID,  COLUMN_TYPE, COLUMN_LEVEL, COLUMN_XP};
    //אובייקט מובנה אחראי מסד נתונים Sql Lite
    SQLiteDatabase database;

    public SkillOpenHelper(Context context) {//פעולה בונה
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//יצירת מסד נתונים
        db.execSQL(CREATE_TABLE_ALL_SKILLS);
        Log.i("data", "Table user created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILL);
        onCreate(db);
    }

    public void open() {//פתיחת מסד נתונים
        database = this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public Skill createUser(Skill c) {//יצירת רשומה
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, c.getType());
        //values.put(COLUMN_LEVEL, c.getLevel());
        values.put(COLUMN_XP, c.getXp());

        long insertId = database.insert(UserOpenHelper.TABLE_USER, null, values);
        Log.i("data", "User " + insertId + "insert to database");
        return c;
    }

    public Skill getUserbyID(int id) {
        Cursor cursor = database.query(UserOpenHelper.DATABASENAME, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idc = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                if (idc == id) {
                    String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                    String level = cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL));
                    int xp = cursor.getInt(cursor.getColumnIndex(COLUMN_XP));
                    Skill c = new Skill(xp, level, type);
                    return c;
                }
            }
        }
        return null;
    }

    public long updateByRow(User c) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, c.getEmail());
        values.put(COLUMN_LEVEL, c.getPassword());
        return database.update(TABLE_SKILL, values, COLUMN_ID +"=" + c.getId(), null);
    }

    public long deleteAll() {return database.delete(SkillOpenHelper.TABLE_SKILL, null, null);}

    public long deleteByRow(long rowId) {
        long deleteid = database.delete(SkillOpenHelper.TABLE_SKILL, COLUMN_ID +"=" + rowId, null);
        Log.i("data", "Spending " + deleteid + "insert to database");
        return deleteid;
    }




    public ArrayList<Skill> getAllSkills() {

        ArrayList<Skill> l = new ArrayList<Skill>();
        Cursor cursor = database.query(UserOpenHelper.TABLE_USER, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                long id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));

                String level = cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL));

                int xp = cursor.getInt(cursor.getColumnIndex(COLUMN_XP));

                Skill s = new Skill(xp, level, type);
                s.setId(id);
                l.add(s);
            }
        }
        Log.i("data", "all skills");
        return l;
    }
}

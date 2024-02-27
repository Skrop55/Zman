package com.example.zman.UserHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class UserOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "DBusers"; //שם מסד נתונים
    public static final String TABLE_USER = "tblUser"; //שם הטבלה
    public static final int DATABASEVERSION = 2;

    public static final String COLUMN_ID = "ID";//מפתח ראשי - מספור אוטומטי
    public static final String COLUMN_NAME = "Gmail";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_IMAGE = "image";


    //Here I'm creating a new table
    private static final String CREATE_TABLE_ALL_USERS = "CREATE TABLE IF NOT EXISTS " +
            TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +  COLUMN_NAME + " VARCHAR, " + COLUMN_PASS + " VARCHAR, " + COLUMN_IMAGE + " \" BLOB,\");";

    //מערך כולל שמות השדות(עמודות)
    String[] allColumns = {COLUMN_ID,  COLUMN_NAME, COLUMN_PASS, COLUMN_IMAGE};
    SQLiteDatabase database; // database type

    public UserOpenHelper(Context context) {//פעולה בונה
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//יצירת מסד נתונים
        db.execSQL(CREATE_TABLE_ALL_USERS);
        Log.i("data", "Table user created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() {//פתיחת מסד נתונים
        database = this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public User createUser(User c) {//יצירת רשומה
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_IMAGE, c.getPicture());


        long insertId = database.insert(UserOpenHelper.TABLE_USER, null, values);
        Log.i("data", "User " + insertId + "insert to database");
        return c;
    }

    public User getUserbyID(int id) {
        Cursor cursor = database.query(UserOpenHelper.DATABASENAME, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                long idc = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
                if (id == idc) {
                    User c = new User(email, password, image);
                    c.setId(idc);
                    return c;
                }
            }
        }
        return null;
    }

    public User getUserbyEmail(String email) {
        Cursor cursor = database.query(UserOpenHelper.DATABASENAME, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                long idc = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String emailc = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String passwordc = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                byte[] imagec = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
                if (email.equals(emailc)) {
                    User c = new User(emailc, passwordc, imagec);
                    c.setId(idc);
                    return c;
                }
            }
        }
        return null;
    }

        public long updateByRow(User c) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());
            values.put(COLUMN_IMAGE, c.getPicture());
        return database.update(TABLE_USER, values, COLUMN_ID +"=" + c.getId(), null);
    }

    public long deleteAll() {return database.delete(UserOpenHelper.TABLE_USER, null, null);}

    public long deleteByRow(long rowId) {
        long deleteid = database.delete(UserOpenHelper.TABLE_USER, COLUMN_ID +" = " + rowId, null);
        Log.i("data", "Spending " + deleteid + "insert to database");
        return deleteid;
    }




    public ArrayList<User> getAllUsers() {// שליפת רשימת מכוניות מתוך טבלה

        ArrayList<User> l = new ArrayList<User>();//אתחול רשימה
        //שאילתת בחירה
        Cursor cursor = database.query(UserOpenHelper.TABLE_USER, allColumns, null, null, null, null, null);
        //אם מספר שורות גדול מ0
        if (cursor.getCount() > 0) {
            //כל עוד שניתן להתקדם בטבלה
            while (cursor.moveToNext()) {
                //העתקת נתונים למשתנים
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String pass = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));
                //יצירת מכונית
                User u = new User(name, pass, image);
                //עדכון מפתח ראשי
                u.setId(id);
                //הוספת מכונית לרשימת מכוניות
                l.add(u);
            }
        }
        Log.i("data", "all users");
        return l;
    }
}

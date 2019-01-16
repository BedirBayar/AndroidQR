package com.example.bedir.qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "university2.db";
    public static final String TABLE_NAME = "faculty";
    public static final String TABLE_2 = "UrlTable";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID TEXT ,Name TEXT, Info TEXT,Since TEXT)");
        db.execSQL("create table "+ TABLE_2 + "(URL TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_2);
        onCreate(db);
    }
    public void insertFaculty(FacultyInfo facultyInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID", facultyInfo.getId());
        values.put("Name", facultyInfo.getName());
        values.put("Info", facultyInfo.getInfo());
        values.put("Since", facultyInfo.getSince());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void insertUrl(String Url){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("URL",Url);
        db.insert(TABLE_2,null,values);
        db.close();
    }
    public boolean getUrl(String url){
        String check;
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_2 + " WHERE URL ='"+url+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            check=cursor.getString(0);
            cursor.close();
            db.close();
            return(check.equals(url));
        }

        else db.close();

        return false;
    }

    public List<FacultyInfo> getAllFaculty() {
        List<FacultyInfo> facultyInfoList = new ArrayList<FacultyInfo>();
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "SELECT  * FROM " + TABLE_NAME;
         Cursor cursor = db.rawQuery(sqlQuery, null);
    //    Cursor cursor = db.query(TABLE_NAME,, null, null, null, null, null);
        while (cursor.moveToNext()) {
            FacultyInfo facultyInfo = new FacultyInfo();
            facultyInfo.setId(String.valueOf(cursor.getInt(0)));
            facultyInfo.setName(cursor.getString(1));
            facultyInfo.setInfo(cursor.getString(2));
            facultyInfo.setSince(cursor.getString(3));
            facultyInfoList.add(facultyInfo);
        }
        return facultyInfoList;
    }

    public FacultyInfo getOneFaculty(String key) {
        FacultyInfo facultyInfo = new FacultyInfo();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE ID ='"+key+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            facultyInfo.setId(cursor.getString(0));
            facultyInfo.setName(cursor.getString(1));
            facultyInfo.setInfo(cursor.getString(2));
            facultyInfo.setSince(cursor.getString(3));
        }
        cursor.close();
        db.close();
       while (cursor.moveToNext()) {
            facultyInfo.setId(String.valueOf(cursor.getString(0)));
            facultyInfo.setName(cursor.getString(1));
            facultyInfo.setInfo(cursor.getString(2));
            facultyInfo.setSince(cursor.getString(3));
        }
        return facultyInfo;
    }
}

package com.example.sqlproject3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public DBHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {


        MyDB.execSQL("create table users(username TEXT primary key, password TEXT ,fname TEXT ,lname TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop table if exists users");

    }
    public boolean insertData(String username, String password,String fname, String lname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("fname", fname);
        values.put("lname", lname);

        long result = db.insert("users", null, values);
        if (result==-1) return false;
        else
            return true;
    }
    public boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Integer deletedata(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("users","username=?", new String[]{username});
    }
    public boolean updatedata(String username,String password ,String user,String fname , String lname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("password",password);
        values.put("fname",fname);
        values.put("lname",lname);


        //db.update("users",contentValues,"username=?",new String[]{username});
        //return true;

        long result = db.update("users",values, "username=?",new String[] {user});
        if (result==-1) return false;
        else
            return true;
    }


    public Cursor selectdata(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor dis = db.rawQuery("select * from users",null  );
        return  dis;
    }


}

package com.example.lab12.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "courseapp.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USER_SQL_ENTRIES =
                " CREATE TABLE " + UserInfo.Users.TABLE_NAME + " (" +
                        UserInfo.Users._ID + " INTEGER PRIMARY KEY,"+
                        UserInfo.Users.COLUMN_NAME_NAME + " TEXT,"+
                        UserInfo.Users.COLUMN_NAME_PASSWORD + " TEXT,"+
                        UserInfo.Users.COLUMN_NAME_TYPE + " TEXT)";

        String CREATE_MESSAGE_SQL_ENTRIES =
                " CREATE TABLE " + UserInfo.Message.TABLE_NAME + " (" +
                        UserInfo.Message._ID + " INTEGER PRIMARY KEY,"+
                        UserInfo.Message.COLUMN_NAME_NAME + " TEXT,"+
                        UserInfo.Message.COLUMN_NAME_SUBJECT + " TEXT,"+
                        UserInfo.Message.COLUMN_NAME_MESSAGE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_USER_SQL_ENTRIES);
        sqLiteDatabase.execSQL(CREATE_MESSAGE_SQL_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addInfoUser(String name,String password,String type){

        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserInfo.Users.COLUMN_NAME_NAME,name);
        values.put(UserInfo.Users.COLUMN_NAME_PASSWORD,password);
        values.put(UserInfo.Users.COLUMN_NAME_TYPE,type);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserInfo.Users.TABLE_NAME, null, values);

        if(newRowId >= 0){
            return  true;
        }
        else
            return false;

    }

    public boolean addInfoMessage(String user,String subject,String message){

        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserInfo.Message.COLUMN_NAME_NAME,user);
        values.put(UserInfo.Message.COLUMN_NAME_SUBJECT,subject);
        values.put(UserInfo.Message.COLUMN_NAME_MESSAGE,message);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserInfo.Message.TABLE_NAME, null, values);

        if(newRowId >= 0){
            return  true;
        }
        else
            return false;

    }

    public ArrayList readAllMessage(){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UserInfo.Message._ID,
                UserInfo.Message.COLUMN_NAME_NAME,
                UserInfo.Message.COLUMN_NAME_SUBJECT,
                UserInfo.Message.COLUMN_NAME_MESSAGE

        };



// How you want the results sorted in the resulting Cursor
        String sortOrder = UserInfo.Message._ID + " DESC";

        Cursor cursor = db.query(
                UserInfo.Message.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList msg = new ArrayList<>();

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Message._ID));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Message.COLUMN_NAME_MESSAGE));
            msg.add(id);//index 0
            msg.add(message);//index 1
        }
        return  msg;
    }

    public List login(String un,String pw){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserInfo.Users._ID,
                UserInfo.Users.COLUMN_NAME_TYPE
//                UserInfo.Users.COLUMN_NAME_NAME,
//                UserInfo.Users.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserInfo.Users.COLUMN_NAME_NAME + " = ? AND " + UserInfo.Users.COLUMN_NAME_PASSWORD + " = ? ";
        String[] selectionArgs = { un, pw };

// How you want the results sorted in the resulting Cursor


        Cursor cursor = db.query(
                UserInfo.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List validUser = new ArrayList<>();

        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Users._ID));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Users.COLUMN_NAME_TYPE));
            validUser.add(id);
            validUser.add(type);
        }

            return validUser;
    }

    public ArrayList readMsg(String id){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
             UserInfo.Message.COLUMN_NAME_MESSAGE,
             UserInfo.Message.COLUMN_NAME_SUBJECT

        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserInfo.Message._ID + " = ?";
        String[] selectionArgs = { id };



        Cursor cursor = db.query(
                UserInfo.Message.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList arrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            String msg = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Message.COLUMN_NAME_MESSAGE));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow(UserInfo.Message.COLUMN_NAME_SUBJECT));
            arrayList.add(msg);
            arrayList.add(subject);
        }
        return arrayList;
    }
}

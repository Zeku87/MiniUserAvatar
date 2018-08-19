package com.demo.joseezequielgallardo.miniuseravatar.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "People";
    private static int DATABASE_VERSION = 1;

    private final String USER_TABLE_NAME = "Users";
    private final String USER_TABLE_CREATION = "CREATE TABLE " + USER_TABLE_NAME +
            "( name TEXT NOT NULL, email TEXT PRIMARY KEY, avatar TEXT NOT NULL)";


    private static DatabaseHandler databaseHandler;

    public static DatabaseHandler getInstance(Context context){
        if(databaseHandler == null){
            databaseHandler = new DatabaseHandler(context);
        }

        return databaseHandler;
    }

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USER_TABLE_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /// INSERTION ///
    public boolean insertUser(String name, String email, String imagePath){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("avatar", imagePath);

        long resultCode = database.insert(USER_TABLE_NAME, null, values);

        values.clear();
        database.close();

        return resultCode > 0;
    }
}

package com.example.ammaryasser.mycolors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ColorDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "colors.db",
            TABLE_NAME = "favorites",
            COL_ID = "ID",
            COL_RED = "RED",
            COL_GREEN = "GREEN",
            COL_BLUE = "BLUE",
            COL_HEX = "HEX";

    ColorDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RED + " INTEGER, " +
                COL_GREEN + " INTEGER, " +
                COL_BLUE + " INTEGER, " +
                COL_HEX + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean insert(int red, int green, int blue, String hex) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_RED, red);
        contentValues.put(COL_GREEN, green);
        contentValues.put(COL_BLUE, blue);
        contentValues.put(COL_HEX, hex);

        return getWritableDatabase().insert(TABLE_NAME, null, contentValues) != -1;
    }

    boolean delete(int id) {
        return getWritableDatabase().delete(TABLE_NAME, COL_ID + " = ?", new String[]{id + ""}) > 0;
    }

    ArrayList<FavoriteColor> getAllColors() {
        Cursor result = getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<FavoriteColor> colors = new ArrayList<>();

        if (result.getCount() > 0) while (result.moveToNext())
            colors.add(new FavoriteColor(
                    result.getInt(0),
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getString(4)));
        else return null;
        result.close();
        return colors;
    }
}

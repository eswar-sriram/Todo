package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="todo.db";
    private static final String TABLE_NAME="todolist";
    private static final String COLUMN1="list";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+COLUMN1+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertdata(String data){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN1,data);
        long r=db.insert(TABLE_NAME,null,contentValues);
        return r!=-1;
    }

    public Cursor getalldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] col={COLUMN1};
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

    public void deletedata(String data){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] where={data};
        db.delete(TABLE_NAME,"list=?",where);
    }
}

package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseActivity extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Student.db";
    private  static final String TABLE_NAME="studentDetials";
    private  static final String ID="_id";
    private  static final String NAME="Name";
    private  static final String AGE="Age";

    private static final String CREATE="CREATE TABLE  "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,  "+NAME+"  VARCHAR(100), "+AGE+"  VARCHAR(20) );";
    private static final String DROP="DROP TABLE "+TABLE_NAME;
    private static final String SELECT="SELECT * FROM "+TABLE_NAME+";";
    private  static final int VERSION_NUMBER=1;
    Context context;
    public DataBaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            db.execSQL(CREATE);
            Toast.makeText(context,"Table is Created",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context,"Exception:"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(DROP);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exception:"+e,Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String name,String age){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(NAME,name);
        contentValues.put(AGE,age);

        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery(SELECT,null);
        return cursor;
    }
}

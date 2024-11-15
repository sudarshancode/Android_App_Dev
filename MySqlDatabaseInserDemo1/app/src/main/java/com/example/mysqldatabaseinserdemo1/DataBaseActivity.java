package com.example.mysqldatabaseinserdemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.concurrent.ExecutionException;

public class DataBaseActivity extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="student.db";
    private static  final String TABLE_NAME="student_detials";
    private static  final String ID="_id";
    private static  final String EMAIL="Email";
    private static  final String PASSWORD="Password";
    private static  final String CREATE_COMMAND="CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EMAIL+" VARCHAR(100), "+PASSWORD+" VARCHAR(100) );";
    private static  final String DROP_COMMAND="DROP TABLE " + TABLE_NAME;
    private static  final int VERSION_NUMBER=1;
    Context context;


    public DataBaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context,"Table Crated",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_COMMAND);

        }catch (Exception e){
            Toast.makeText(context,"Exeception:"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(DROP_COMMAND);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exeception:"+e,Toast.LENGTH_SHORT).show();
        }
    }
    public long insertData(String email,String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(EMAIL,email);
        contentValues.put(PASSWORD,password);

        return (long) sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
}

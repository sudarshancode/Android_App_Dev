package com.example.mysqldatabasedemo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseActivity extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Studnet.db";
    public static final String TABLE_NAME="student_detials";
    public static final String ID="_id";
    public static final String NAME="Name";
    public static final String GENDER="Gender";
    public static final String AGE="Age";
    public static final int VERSION_NUMBER=1;

    public static final String DROP_TABLE="DROP TABLE "+TABLE_NAME+" ;";
    public  static  final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(50), "+GENDER+" VARCHAR(20), "+AGE+" INTEGER ); ";

    Context context;
    public DataBaseActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Table created",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context,"Exception:"+e,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context,"Upgrade Database",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exception:"+e,Toast.LENGTH_SHORT).show();
        }

    }
}

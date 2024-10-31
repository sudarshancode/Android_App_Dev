package com.example.sqlitedatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseConnectivity extends SQLiteOpenHelper {
    Context context;
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="userData.db";
    public static final String TABLE="new_user";
    public static final String ID="_id";
    public static final String NAME="name";
    public static final String LastName="last_name";
    public static final String AGE="age";
    public static final String queary="create table "+ TABLE +" ("+ID+" INTEGER,"+NAME+" VARCHAR(20), "+LastName+" VARCHAR(20), "+AGE+" INTEGER);";
    public static final String DROP_TABLE="drop table if exists "+TABLE;
    public DatabaseConnectivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            sqLiteDatabase.execSQL(queary);

            Toast.makeText(context,"On Create is called",Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context,"Exception:"+e,Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context,"On Upgrade is called",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        } catch (Exception e) {
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}

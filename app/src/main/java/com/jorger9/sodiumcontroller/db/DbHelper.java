package com.jorger9.sodiumcontroller.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by jorger9 on 11/20/16.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "sodiumController.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.createTableRestrictions());
        db.execSQL(DBContract.createTableUserConfigs());
        db.execSQL(DBContract.createTableFoodGroups());
        db.execSQL(DBContract.createTableFoods());
        db.execSQL(DBContract.createTableDailyFoods());
        db.execSQL(DBContract.loadRestrinctionsData());

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DBContract.deleteTableDailyFoods());
        db.execSQL(DBContract.deleteTableFoods());
        db.execSQL(DBContract.deleteTableFoodGroups());
        db.execSQL(DBContract.deleteTableUserConfigs());
        db.execSQL(DBContract.deleteTableRestrictions());
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

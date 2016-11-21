package com.jorger9.sodiumcontroller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.db.DBContract;
import com.jorger9.sodiumcontroller.db.DbHelper;
import com.jorger9.sodiumcontroller.view.ContainerActivity;
import com.jorger9.sodiumcontroller.view.ShowInfoActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.SodiumRestriction.COLUMN_UPPER_LIMIT, "100");
        values.put(DBContract.SodiumRestriction.COLUMN_LOWER_LIMIT, "200");


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.SodiumRestriction.TABLE_NAME, null, values);

        Toast.makeText(this, "" +newRowId,Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goShowInfo(View view)
    {
        Intent intent = new Intent(this, ShowInfoActivity.class);
        startActivity(intent);
    }


    public void goContainerActivity(View view)
    {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }


}

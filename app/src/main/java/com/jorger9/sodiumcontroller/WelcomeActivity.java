package com.jorger9.sodiumcontroller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                DBContract.SodiumRestriction._ID,
                DBContract.SodiumRestriction.COLUMN_LOWER_LIMIT,
                DBContract.SodiumRestriction.COLUMN_UPPER_LIMIT
        };

// Filter results WHERE "title" = 'My Title'
       // String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
      //  String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
       // String sortOrder =
                //FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                DBContract.SodiumRestriction.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();
        long itemId = c.getLong(
                c.getColumnIndexOrThrow(DBContract.SodiumRestriction._ID)
        );

        Toast.makeText(this,""+c.getColumnIndexOrThrow(DBContract.SodiumRestriction.COLUMN_LOWER_LIMIT),Toast.LENGTH_SHORT).show();

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

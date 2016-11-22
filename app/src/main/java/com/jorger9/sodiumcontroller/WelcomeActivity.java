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
import com.jorger9.sodiumcontroller.model.Restriction;
import com.jorger9.sodiumcontroller.view.ContainerActivity;
import com.jorger9.sodiumcontroller.view.ShowInfoActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmQuery<Restriction> query = realm.where(Restriction.class);
        RealmResults<Restriction> result1 = query.findAll();

        Toast.makeText(this,""+result1.size(),Toast.LENGTH_LONG).show();


        if (result1.size() == 0){
            realm.close();
            Restriction r = new Restriction();
            r.loadData();
         }



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

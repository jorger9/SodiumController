package com.jorger9.sodiumcontroller.view;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.DailyFood;
import com.jorger9.sodiumcontroller.model.Food;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class AddDailyFoodActivity extends AppCompatActivity {

    private Realm realm;
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       realm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily_food);

        showToolbar("Agregar comida",true);

        Intent intent = getIntent();

        long foodId = intent.getLongExtra("foodId",0);

        if(!realm.isInTransaction())realm.beginTransaction();

        food = realm.where(Food.class).equalTo("id",foodId).findFirst();

        TextView textView = (TextView)findViewById(R.id.textview_selectedfood);
        textView.setText(food.getFoodName());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void showToolbar(String title, boolean upButton){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    public void addDailyFood(View view){


        TextInputEditText edidText = (TextInputEditText) findViewById(R.id.foodQuantity);

        if(edidText.getText().length()>0){
            Double grQuantity = Double.parseDouble(edidText.getText().toString());
            Double mgQuantity = (grQuantity/100)*food.getSodiumMg();

            Calendar today = Calendar.getInstance();
            today.set(Calendar.MILLISECOND, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.HOUR_OF_DAY, 0);

            DailyFood dailyFood = new DailyFood();
            dailyFood.setId(dailyFood.getNewId());
            dailyFood.setFood(food);
            dailyFood.setQuantity(grQuantity);
            dailyFood.setMgQuantity(mgQuantity);
            dailyFood.setDate(today.getTime());


            if(!realm.isInTransaction()) realm.beginTransaction();
            realm.copyToRealmOrUpdate(dailyFood);
            realm.commitTransaction();
            Toast.makeText(this,"Comida agregada con exito",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,ContainerActivity.class);
           // intent.putExtra("groupId",food.getFoodGroup().getId());
            startActivity(intent);
        }

        else {
            Toast.makeText(this,"Introduzca una cantidad en gramos",Toast.LENGTH_SHORT).show();
        }




    }

}

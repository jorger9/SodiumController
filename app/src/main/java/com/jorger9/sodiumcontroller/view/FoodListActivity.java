package com.jorger9.sodiumcontroller.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.adapter.FoodListAdapterRecyclerView;
import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.model.FoodGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FoodListActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Intent intent = getIntent();
        long groupID = intent.getLongExtra("groupId",0);

        showToolbar("Grupos Alimenticios",true);

        RecyclerView foodListRecycler = (RecyclerView) findViewById(R.id.foodListRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        foodListRecycler.setLayoutManager(linearLayoutManager);

        FoodListAdapterRecyclerView foodListAdapterRecyclerView =
                new FoodListAdapterRecyclerView(buildFoods(groupID), R.layout.cardview_foodlist, this);
        foodListRecycler.setAdapter(foodListAdapterRecyclerView);
    }

    public ArrayList<Food> buildFoods(long groupID){
        ArrayList<Food> foods = new ArrayList<>();

        if(!realm.isInTransaction()) realm.beginTransaction();

        RealmResults<Food> results = realm.where(Food.class).equalTo("foodGroup.id",groupID).findAll();

        for (Food food :results) {
            foods.add(food);

        }

        return  foods;
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
}

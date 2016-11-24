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
    private String toolBarTitle;
    private RecyclerView recyclerView;
    private long groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Intent intent = getIntent();
         groupID = intent.getLongExtra("groupId",0);

        if(!realm.isInTransaction()) realm.beginTransaction();


        toolBarTitle  = groupID==0 ?  "Lista de alimentos" : realm.where(FoodGroup.class).equalTo("id",groupID).findFirst().getGroupName();

        showToolbar(toolBarTitle,true);

        recyclerView = (RecyclerView) findViewById(R.id.foodListRecycler);
        setUpRecyclerView();

    }


    public void setUpRecyclerView(){

        ArrayList<Food> foodList ;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());

        foodList = groupID == 0 ? buildFoods() : buildFoods(groupID);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);


        FoodListAdapterRecyclerView foodListAdapterRecyclerView =
                new FoodListAdapterRecyclerView(foodList, R.layout.cardview_foodlist, this);
        recyclerView.setAdapter(foodListAdapterRecyclerView);

    }

    public ArrayList<Food> buildFoods(){
        ArrayList<Food> foods = new ArrayList<>();

        if(!realm.isInTransaction()) realm.beginTransaction();

        RealmResults<Food> results = realm.where(Food.class).findAll().sort("foodName");

        for (Food food :results) {
            foods.add(food);

        }

        return  foods;
    }
    public ArrayList<Food> buildFoods(long groupID){
        ArrayList<Food> foods = new ArrayList<>();

        if(!realm.isInTransaction()) realm.beginTransaction();

        RealmResults<Food> results = realm.where(Food.class).equalTo("foodGroup.id",groupID).findAll().sort("foodName");

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

    public void goToAddDailyFood(Food food){
        Intent intent = new Intent(this,AddDailyFoodActivity.class);

        intent.putExtra("foodId",food.getId());

        startActivity(intent);

    }
}

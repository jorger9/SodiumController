package com.jorger9.sodiumcontroller.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.model.FoodGroup;
import com.jorger9.sodiumcontroller.view.FoodListActivity;

import java.util.ArrayList;

/**
 * Created by jorger9 on 11/22/16.
 */

public class FoodListAdapterRecyclerView extends RecyclerView.Adapter<FoodListAdapterRecyclerView.FoodListViewHolder>{

    private ArrayList<Food> foods;
    private int resource;
    private FoodListActivity activity;

    public FoodListAdapterRecyclerView(ArrayList<Food> foods, int resource, FoodListActivity activity) {
        this.foods = foods;
        this.resource = resource;
        this.activity = activity;
    }



    @Override
    public FoodListAdapterRecyclerView.FoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return  new FoodListAdapterRecyclerView.FoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodListViewHolder holder, int position) {

        Food food = foods.get(position);
        holder.foodName.setText( food.getFoodName());
        holder.foodGr.setText( "100gr");
        holder.foodSodiumContent.setText(food.getSodiumMg()+"mg");
        holder.food = food;

    }


    public int getItemCount() {
        return foods.size();
    }

    class FoodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView foodName;
        public TextView foodGr;
        public TextView foodSodiumContent;
        public Food food;


        public FoodListViewHolder(View view) {
        super(view);
            foodName = (TextView) view.findViewById(R.id.foodlistNameCard);
            foodGr = (TextView) view.findViewById(R.id.foodlistquantity);
            foodSodiumContent = (TextView) view.findViewById(R.id.foodlistsodiumContent);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            activity.goToAddDailyFood(food);

        }
     }
}

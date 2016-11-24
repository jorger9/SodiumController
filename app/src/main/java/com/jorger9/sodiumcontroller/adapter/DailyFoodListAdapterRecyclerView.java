package com.jorger9.sodiumcontroller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.DailyFood;
import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.view.FoodListActivity;
import com.jorger9.sodiumcontroller.view.fragment.HomeFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by jorger9 on 11/22/16.
 */

public class DailyFoodListAdapterRecyclerView extends RecyclerView.Adapter<DailyFoodListAdapterRecyclerView.DaylyFoodListViewHolder>{

    private ArrayList<DailyFood> foods;
    private int resource;
    private HomeFragment activity;

    public DailyFoodListAdapterRecyclerView(ArrayList<DailyFood> foods, int resource, HomeFragment activity) {
        this.foods = foods;
        this.resource = resource;
        this.activity = activity;
    }



    @Override
    public DailyFoodListAdapterRecyclerView.DaylyFoodListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return  new DailyFoodListAdapterRecyclerView.DaylyFoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DaylyFoodListViewHolder holder, int position) {

        DailyFood food = foods.get(position);
        holder.foodName.setText( food.getFood().getFoodName());
        holder.foodGr.setText(food.getQuantity()+"gr");
        holder.foodSodiumContent.setText(new DecimalFormat("##.##").format(food.getMgQuantity())+"mg");
        holder.food = food;

    }


    public int getItemCount() {
        return foods.size();
    }

    class DaylyFoodListViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView foodName;
        public TextView foodGr;
        public TextView foodSodiumContent;
        public DailyFood food;


        public DaylyFoodListViewHolder(View view) {
        super(view);
            foodName = (TextView) view.findViewById(R.id.foodNameCard);
            foodGr = (TextView) view.findViewById(R.id.foodgrcard);
            foodSodiumContent = (TextView) view.findViewById(R.id.sodiumContentCard);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v){
            activity.confirmDelete(food);
            return true;

        }
     }
}

package com.jorger9.sodiumcontroller.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.model.FoodGroup;

import com.jorger9.sodiumcontroller.model.FoodGroup;

import java.util.ArrayList;

import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by jorger9 on 11/22/16.
 */

public class FoodGroupAdapterRecyclerView extends RecyclerView.Adapter<FoodGroupAdapterRecyclerView.FoodGroupViewHolder> {

    private ArrayList<FoodGroup> groups;
    private int resource;
    private Activity activity;


    public FoodGroupAdapterRecyclerView(ArrayList<FoodGroup> groups, int resource, Activity activity) {
        this.groups = groups;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public FoodGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return  new FoodGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodGroupViewHolder holder, int position) {
        FoodGroup foodGroup = groups.get(position);
        holder.groupFoodTitle.setText(foodGroup.getGroupName());

    }

    public int getItemCount() {
        return groups.size();
    }

    class FoodGroupViewHolder extends RecyclerView.ViewHolder {
        public ImageView groupFoodImage;
        public TextView groupFoodTitle;

        public FoodGroupViewHolder(View view) {
            super(view);
            groupFoodImage = (ImageView) view.findViewById(R.id.imageView_foodgroupcard);
            groupFoodTitle = (TextView) view.findViewById(R.id.textView_foodgroupcard);
        }

    }

}

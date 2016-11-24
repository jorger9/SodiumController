package com.jorger9.sodiumcontroller.adapter;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.FoodGroup;
import com.jorger9.sodiumcontroller.view.FoodListActivity;

import java.util.ArrayList;


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
        final FoodGroup foodGroup = groups.get(position);
        long imageId;
        /* write(1,"Verduras","vegetables.png");
        write(2,"Frutas","fruits.png");
        write(3,"Cereales","cereals.png");
        write(4,"Carnes y vísceras","animalorigin.png");
        write(5,"Oleaginosas","oleginosas.png");
        write(6,"Leguminosas","legumes.png");
        write(7,"Productos lácteos ","dairyProducts.png");
        write(8,"Aceites y grasas","oils.png");
        write(9,"Industrializados","processed.png");*/

        int[] a = new int[9];
        a[0] = R.drawable.vegetables;
        a[1] = R.drawable.fruits;
        a[2] = R.drawable.cereals;
        a[3] = R.drawable.animalorigin;
        a[4] = R.drawable.oleginosas;
        a[5] = R.drawable.legumes;
        a[6] = R.drawable.dairyproducts;
        a[7] = R.drawable.oils;
        a[8] = R.drawable.processed;

        int i = Integer.parseInt(""+(foodGroup.getId()-1));
        holder.groupFoodTitle.setText(foodGroup.getGroupName());
        holder.groupFoodImage.setImageResource(a[i]);

        holder.groupFoodImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(activity, FoodListActivity.class);
                intent.putExtra("groupId",foodGroup.getId());
                activity.startActivity(intent);
            }
        });

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

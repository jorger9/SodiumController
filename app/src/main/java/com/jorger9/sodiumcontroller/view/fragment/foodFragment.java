package com.jorger9.sodiumcontroller.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.adapter.FoodGroupAdapterRecyclerView;
import com.jorger9.sodiumcontroller.model.FoodGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
private Realm realm;

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        showToolbar("Grupos Alimenticios",false,view);
        RecyclerView foodGroupsRecycler = (RecyclerView) view.findViewById(R.id.foodGroupRecycler);

        GridLayoutManager gridLayoutMaganer = new GridLayoutManager(getContext(),2);


        foodGroupsRecycler.setLayoutManager(gridLayoutMaganer);

        FoodGroupAdapterRecyclerView foodGroupAdapterRecyclerView =
                new FoodGroupAdapterRecyclerView(buildGroups(), R.layout.cardview_foodgroup, getActivity());
        foodGroupsRecycler.setAdapter(foodGroupAdapterRecyclerView);


        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.close();
    }

    public ArrayList<FoodGroup> buildGroups(){
        ArrayList<FoodGroup> groups = new ArrayList<>();

        if(!realm.isInTransaction()) realm.beginTransaction();

        RealmResults<FoodGroup>  results = realm.where(FoodGroup.class).findAll();

        for (FoodGroup group :results) {
            groups.add(group);

        }

        return  groups;
    }


    public void showToolbar(String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}

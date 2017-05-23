package com.jorger9.sodiumcontroller.view.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.adapter.DailyFoodListAdapterRecyclerView;
import com.jorger9.sodiumcontroller.adapter.FoodListAdapterRecyclerView;
import com.jorger9.sodiumcontroller.model.DailyFood;
import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.model.UserConfig;
import com.jorger9.sodiumcontroller.view.FoodListActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Realm realm;
    private ProgressBar progressBar;
    private double progressStatus;
    private TextView lowerLimit;
    private TextView upperLimit;
    private TextView total;
    private View view;

    private RecyclerView recyclerView;


    public HomeFragment() {
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        realm = Realm.getDefaultInstance();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        lowerLimit = (TextView)view.findViewById(R.id.lowerlimit_barindicator);
        upperLimit = (TextView)view.findViewById(R.id.upperlimit_barindicator);
         total = (TextView)view.findViewById(R.id.totalsodium);


        recyclerView = (RecyclerView)view.findViewById(R.id.dailyfoodListRecycler);
        setUpRecyclerView();
        setUpProgressBar();

        showToolbar("Home",false,view);

        return view;
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

    public void showToolbar(String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }


    public void setUpRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(linearLayoutManager);

        DailyFoodListAdapterRecyclerView adapter =
                new DailyFoodListAdapterRecyclerView(buildFoods(), R.layout.cardview_food, this);
        recyclerView.setAdapter(adapter);

    }

    public void setUpProgressBar(){
        int lLimit, uLimit, limitSize;

        double consum = 0;



        if(!realm.isInTransaction())realm.beginTransaction();

        UserConfig UserConfig = realm.where(UserConfig.class).findAll().last();

        lLimit =0;
        uLimit = UserConfig.getRestriction().getUpperLimit();
        Calendar today = Calendar.getInstance();

        today.set(Calendar.MILLISECOND, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);

        RealmResults<DailyFood> results = realm.where(DailyFood.class).equalTo("date",today.getTime()).findAll();

        for (DailyFood food :results) {
            consum += food.getMgQuantity();

        }

        total.setText(new DecimalFormat("##.##").format(consum)+"mg de sodio");
         limitSize = uLimit-lLimit;
        //consum = consum-lLimit;

        progressStatus = (consum * 100)/limitSize;

        progressStatus = Math.round(progressStatus);



        progressBar.setProgress(((int) progressStatus));

        lowerLimit.setText(lLimit+"mg");
        upperLimit.setText(uLimit+"mg");

        if(progressStatus>=75 && progressStatus <100)
            Toast.makeText(getContext(),"Cuidado, casi llegas al límite", Toast.LENGTH_LONG).show();

       else if(progressStatus>=100)
            Toast.makeText(getContext(),"ALTO! Ya no debes consumir mas sodio", Toast.LENGTH_LONG).show();

    }

    public ArrayList<DailyFood> buildFoods(){
        ArrayList<DailyFood> foods = new ArrayList<>();

        Calendar today = Calendar.getInstance();
        today.set(Calendar.MILLISECOND, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);

        if(!realm.isInTransaction()) realm.beginTransaction();

        RealmResults<DailyFood> results = realm.where(DailyFood.class).equalTo("date",today.getTime()).findAll();

        for (DailyFood food :results) {
            foods.add(food);

        }
//Toast.makeText(getContext(),""+foods.size(),Toast.LENGTH_LONG).show();
        return  foods;
    }


    public void deleteDailyFood(DailyFood food){
            final long id = food.getId();

        if(!realm.isInTransaction()) realm.beginTransaction();
                    realm.where(DailyFood.class).equalTo("id", id)
                            .findAll()
                            .deleteAllFromRealm();
        realm.commitTransaction();
        setUpRecyclerView();
        setUpProgressBar();
    }

    public void confirmDelete(final DailyFood food){
        new AlertDialog.Builder(getContext())
                .setTitle("Eliminar comida")
                .setMessage("¿Realmente deseas eliminar esta comida")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteDailyFood(food);
                    }})
                .setNegativeButton("No", null).show();

    }


    public void goToFoodListActivity(View view)
    {
        Intent intent = new Intent(getContext(), FoodListActivity.class);

        startActivity(intent);
    }

}

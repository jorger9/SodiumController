package com.jorger9.sodiumcontroller.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.UserConfig;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Realm realm;
    private ProgressBar progressBar;
    private int progressStatus;


    public HomeFragment() {
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getContext(),""+R.drawable.animalorigin,Toast.LENGTH_LONG).show();


        realm = Realm.getDefaultInstance();
        if(!realm.isInTransaction())realm.beginTransaction();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        TextView lowerLimit = (TextView)view.findViewById(R.id.lowerlimit_barindicator);
        TextView upperLimit = (TextView)view.findViewById(R.id.upperlimit_barindicator);

        UserConfig UserConfig = realm.where(UserConfig.class).findAll().first();

        lowerLimit.setText(UserConfig.getRestriction().getLowerLimit()+"mg");
        upperLimit.setText(UserConfig.getRestriction().getUpperLimit()+"mg");

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
}

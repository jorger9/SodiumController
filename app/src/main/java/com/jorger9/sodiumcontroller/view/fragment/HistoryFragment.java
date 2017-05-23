package com.jorger9.sodiumcontroller.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.model.DailyFood;
import com.jorger9.sodiumcontroller.model.Food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private Realm realm;

    public HistoryFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        realm = Realm.getDefaultInstance();
        showToolbar("Historial de consumo",false,view);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date firstDay = calendar.getTime();


        ArrayList<Date> dates = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            dates .add(calendar.getTime());
            calendar.add(Calendar.DATE,1);
        }





        //RealmResults<DailyFood> results = realm.where(DailyFood.class).between("date",firstDay,lastDay).findAll();




        GraphView graph = (GraphView) view.findViewById(R.id.graph);

        /*BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3),
                new DataPoint(d1, 2),
                new DataPoint(d2, 6)
        });*/



        BarGraphSeries<DataPoint> series = new BarGraphSeries<>();
        if(!realm.isInTransaction())realm.beginTransaction();

        for (Date date:dates) {
            RealmResults<DailyFood> results = realm.where(DailyFood.class).equalTo("date",date).findAll();
            Long sum = results.sum("mgQuantity").longValue();

            series.appendData(new DataPoint(date,sum),true,dates.size());

        }

        series.setSpacing(30);
        series.setColor(R.color.colorAccent);
        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(dates.size()); // only 4 because of the space

// set manual x bounds to have nice steps
        graph.getViewport().setMinX(dates.get(0).getTime());
        graph.getViewport().setMaxX(dates.get(dates.size()-1).getTime());
        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);


        return view;
    }

    public void showToolbar(String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}

package com.jorger9.sodiumcontroller.view;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.jorger9.sodiumcontroller.R;
import com.jorger9.sodiumcontroller.view.fragment.FoodFragment;
import com.jorger9.sodiumcontroller.view.fragment.HistoryFragment;
import com.jorger9.sodiumcontroller.view.fragment.HomeFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private int mProgressStatus = 50;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar);

        bottomBar.setDefaultTab(R.id.home);

        mProgress = (ProgressBar) findViewById(R.id.progress_bar);



        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.home){
                    replaceFragment( new HomeFragment());

                }
                else if(tabId == R.id.food){
                    replaceFragment( new FoodFragment());
                }
                else if(tabId==R.id.history){

                    replaceFragment( new HistoryFragment());
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

}

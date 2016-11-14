package com.jorger9.sodiumcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.view.ContainerActivity;
import com.jorger9.sodiumcontroller.view.ShowInfoActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goShowInfo(View view)
    {
        Intent intent = new Intent(this, ShowInfoActivity.class);
        startActivity(intent);
    }


    public void goContainerActivity(View view)
    {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }


}

package com.jorger9.sodiumcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jorger9.sodiumcontroller.model.Food;
import com.jorger9.sodiumcontroller.model.FoodGroup;
import com.jorger9.sodiumcontroller.model.Restriction;
import com.jorger9.sodiumcontroller.model.UserConfig;
import com.jorger9.sodiumcontroller.view.ContainerActivity;
import com.jorger9.sodiumcontroller.view.ShowInfoActivity;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class WelcomeActivity extends AppCompatActivity {

    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();


        RealmQuery<UserConfig> q = realm.where(UserConfig.class);
        RealmResults<UserConfig> result = q.findAll();

        if(!result.isEmpty()){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            Intent intent = new Intent(this, ContainerActivity.class);
            startActivity(intent);

        }
        else {

            RealmQuery<Restriction> query = realm.where(Restriction.class);
            RealmResults<Restriction> result1 = query.findAll();

            //Toast.makeText(this,""+result1.size(),Toast.LENGTH_LONG).show();

            RealmQuery<Food> query2 = realm.where(Food.class);
            RealmResults<Food> result2 = query2.findAll();

            //Toast.makeText(this,""+result2.size(),Toast.LENGTH_LONG).show();

            if (result1.isEmpty()){

                Restriction r = new Restriction();
                FoodGroup foodGroup = new FoodGroup();
                r.loadData();
                foodGroup.loadData();
                Food food = new Food();
                food.loadData();
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void goShowInfo(View view)
    {

        Intent intent = new Intent(this, ShowInfoActivity.class);
        startActivity(intent);
    }

    public void goContainerActivity(View view)
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupRestrictions);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId < 0){
            Toast.makeText(this,
                    "Debe elegir un nivel de restricción", Toast.LENGTH_SHORT).show();
        }
        else{

                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                String radioText = radioButton.getText().toString();

                if(!realm.isInTransaction()) realm.beginTransaction();
                Restriction result = realm.where(Restriction.class).equalTo("restrinctionName",radioText).findAll().last();
                long id =   realm.where(UserConfig.class).findAll().isEmpty() ? 1 : realm.where(UserConfig.class).findAll().last().getId()+1;

                UserConfig userConfig = new UserConfig();
                userConfig.setId(id);
                userConfig.setRestriction(result);
                userConfig.setStartDatetime(null);
                userConfig.setEndDatetime(null);

                realm.copyToRealmOrUpdate(userConfig);
                realm.commitTransaction();
                Intent intent = new Intent(this, ContainerActivity.class);
                startActivity(intent);

        }

    }

}

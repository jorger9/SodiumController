package com.jorger9.sodiumcontroller;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jorger9 on 11/21/16.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("sodiumcontroller.realm")
                .schemaVersion(3)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

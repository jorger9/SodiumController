package com.jorger9.sodiumcontroller.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jorger9 on 11/21/16.
 */

public class Restriction extends RealmObject {
   @PrimaryKey
    private long id;
    private String restrinctionName;
    private int upperLimit;
    private int lowerLimit;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRestrinctionName() {
        return restrinctionName;
    }

    public void setRestrinctionName(String restrinctionName) {
        this.restrinctionName = restrinctionName;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }


    public void loadData()
    {
        write(1,"Restricción leve",900,2100);
        write(2,"Restricción moderada",500,900);
        write(3,"Restricción severa",0,500);

    }

    private void write(int id, String name, int lowerLimit, int upperLimit){
        Restriction restriction = new Restriction();
        restriction.setId(id);
        restriction.setRestrinctionName(name);
        restriction.setUpperLimit(lowerLimit);
        restriction.setLowerLimit(upperLimit);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(restriction); // Create a new object

        realm.commitTransaction();
        realm.close();

    }
}

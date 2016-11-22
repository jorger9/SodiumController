package com.jorger9.sodiumcontroller.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jorger9 on 11/21/16.
 */

public class FoodGroup extends RealmObject{

    @PrimaryKey
    private long id;
    private String groupName;
    private String groupPicture;

    public String getGroupPicture() {
        return groupPicture;
    }

    public void setGroupPicture(String groupPicture) {
        this.groupPicture = groupPicture;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void loadData()
    {
        //write("Restricción leve",900,2100);
        //write("Restricción moderada",500,900);
        //write("Restricción severa",0,500);

    }

    private void write(int id, String groupName, String groupPicture){
        FoodGroup foodGroup = new FoodGroup();
        foodGroup.setId(id);
        foodGroup.setGroupName(groupName);
        foodGroup.setGroupPicture(groupPicture);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foodGroup);

        realm.commitTransaction();
        realm.close();

    }
}

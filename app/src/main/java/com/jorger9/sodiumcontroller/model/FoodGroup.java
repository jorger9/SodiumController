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

    public FoodGroup(){

    }
    public FoodGroup(long id, String groupName, String groupPicture) {
        this.id = id;
        this.groupName = groupName;
        this.groupPicture = groupPicture;
    }

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
        write(1,"Verduras","vegetables.png");
        write(2,"Frutas","fruits.png");
        write(3,"Cereales","cereals.png");
        write(4,"Carnes y vísceras","animalorigin.png");
        write(5,"Oleaginosas","oleginosas.png");
        write(6,"Leguminosas","legumes.png");
        write(7,"Productos lácteos ","dairyProducts.png");
        write(8,"Aceites y grasas","oils.png");
        write(9,"Industrializados","processed.png");

    }

    private void write(int id, String groupName, String groupPicture){
        final FoodGroup foodGroup = new FoodGroup();
        foodGroup.setId(id);
        foodGroup.setGroupName(groupName);
        foodGroup.setGroupPicture(groupPicture);

        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()) realm.beginTransaction();

        realm.copyToRealmOrUpdate(foodGroup);

        realm.commitTransaction();
        /*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(foodGroup);
            }
        });

        realm.close();*/

    }
}

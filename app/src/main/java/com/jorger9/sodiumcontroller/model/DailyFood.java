package com.jorger9.sodiumcontroller.model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jorger9 on 11/23/16.
 */

public class DailyFood extends RealmObject {

    @PrimaryKey
    private Long id;
    private Food food;
    private double quantity;
    private double mgQuantity;
    private Date date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getMgQuantity() {
        return mgQuantity;
    }

    public void setMgQuantity(double mgQuantity) {
        this.mgQuantity = mgQuantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public  long getNewId()
    {
        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()) realm.beginTransaction();

        return realm.where(DailyFood.class).count()+1;


    }
}

package com.jorger9.sodiumcontroller.model;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jorger9 on 11/21/16.
 */

public class UserConfig extends RealmObject {

    @PrimaryKey
    private long id;
    private Restriction restriction;
    private Date startDatetime;
    private Date endDatetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public void write(Restriction restriction, Date startDatetime, Date endDatetime){
        final UserConfig userConfig = new UserConfig();

        userConfig.setRestriction(restriction);
        userConfig.setEndDatetime(endDatetime);
        userConfig.setStartDatetime(startDatetime);


        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()) realm.beginTransaction();

        realm.copyToRealmOrUpdate(userConfig);

        realm.commitTransaction();
        /*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long id = realm.where(UserConfig.class).findAll().last().getId()+1;
                userConfig.setId(id);
                realm.copyToRealmOrUpdate(userConfig);
            }
        });

        realm.close();*/

    }

    public void write(UserConfig userConfig1){
        final UserConfig userConfig = userConfig1;

        userConfig.setRestriction(restriction);
        userConfig.setEndDatetime(endDatetime);
        userConfig.setStartDatetime(startDatetime);


        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()) realm.beginTransaction();

        realm.copyToRealmOrUpdate(userConfig);

        realm.commitTransaction();
        /*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long id = realm.where(UserConfig.class).findAll().last().getId()+1;
                userConfig.setId(id);
                realm.copyToRealmOrUpdate(userConfig);
            }
        });

        realm.close();*/

    }


}

package com.jorger9.sodiumcontroller.db;

import android.provider.BaseColumns;

/**
 * Created by jorger9 on 11/20/16.
 */

public final class DBContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";


    private DBContract(){}


    public static class SodiumRestriction implements BaseColumns{

        public static final String TABLE_NAME = "restriction";
        public static final String COLUMN_LOWER_LIMIT = "lowerLimit";
        public static final String COLUMN_UPPER_LIMIT = "upperLimit";
        public static final String COLUMN_RESTRICTION_NAME = "restrictionName";
    }

    public static final String createTableRestrictions(){

        return "CREATE TABLE " + SodiumRestriction.TABLE_NAME + " (" +
                SodiumRestriction._ID + " INTEGER PRIMARY KEY," +
                SodiumRestriction.COLUMN_LOWER_LIMIT + INTEGER_TYPE + COMMA_SEP +
                SodiumRestriction.COLUMN_UPPER_LIMIT + INTEGER_TYPE + COMMA_SEP +
                SodiumRestriction.COLUMN_RESTRICTION_NAME + TEXT_TYPE + " )";
    }

    public static final String deleteTableRestrictions(){
        return "DROP TABLE IF EXISTS " + SodiumRestriction.TABLE_NAME;
    }


    public static class UserConfig implements BaseColumns{

        public static final String TABLE_NAME = "userConfig";
        public static final String COLUMN_START_DATETIME = "startDatetime";
        public static final String COLUMN_END_DATETIME = "endDatetime";
        public static final String COLUMN_RESTRICTION_ID = "restrictionID";
    }

    public static final String createTableUserConfigs(){

        return "CREATE TABLE " + UserConfig.TABLE_NAME + " (" +
                UserConfig._ID + " INTEGER PRIMARY KEY," +
                UserConfig.COLUMN_START_DATETIME + TEXT_TYPE + COMMA_SEP +
                UserConfig.COLUMN_END_DATETIME + TEXT_TYPE + COMMA_SEP +
                UserConfig.COLUMN_RESTRICTION_ID + INTEGER_TYPE + COMMA_SEP +
               "FOREIGN KEY("+UserConfig.COLUMN_RESTRICTION_ID+") REFERENCES "
                +SodiumRestriction.TABLE_NAME+"("+SodiumRestriction._ID+")"+" )";
    }

    public static final String deleteTableUserConfigs(){
        return "DROP TABLE IF EXISTS " + UserConfig.TABLE_NAME;
    }

    public static class FoodGroup implements BaseColumns{

        public static final String TABLE_NAME = "foodGroup";
        public static final String COLUMN_GROUP_NAME = "groupName";
        public static final String COLUMN_GROUP_IMAGE = "groupImage";

    }

    public static final String createTableFoodGroups(){

        return "CREATE TABLE " + FoodGroup.TABLE_NAME + " (" +
                FoodGroup._ID + " INTEGER PRIMARY KEY," +
                FoodGroup.COLUMN_GROUP_NAME + TEXT_TYPE + COMMA_SEP +
                FoodGroup.COLUMN_GROUP_IMAGE + TEXT_TYPE +" )";
    }

    public static final String deleteTableFoodGroups(){
        return "DROP TABLE IF EXISTS " + FoodGroup.TABLE_NAME;
    }

    public static class Food implements BaseColumns{

        public static final String TABLE_NAME = "food";
        public static final String COLUMN_FOOD_NAME = "foodName";
        public static final String COLUMN_SODIUM_MG = "sodiumMg";
        public static final String COLUMN_GROUP_ID = "groupId";

    }

    public static final String createTableFoods(){

        return "CREATE TABLE " + Food.TABLE_NAME + " (" +
                Food._ID + " INTEGER PRIMARY KEY," +
                Food.COLUMN_FOOD_NAME + TEXT_TYPE + COMMA_SEP +
                Food.COLUMN_SODIUM_MG + REAL_TYPE + COMMA_SEP +
                Food.COLUMN_GROUP_ID + INTEGER_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + Food.COLUMN_GROUP_ID + ") REFERENCES "
                +FoodGroup.TABLE_NAME + "(" + FoodGroup._ID + ")"+" )";
    }

    public static final String deleteTableFoods(){
        return "DROP TABLE IF EXISTS " + Food.TABLE_NAME;
    }


    public static class DailyFood implements BaseColumns{

        public static final String TABLE_NAME = "food";
        public static final String COLUMN_FOOD_QUANTITY = "foodQuantity";
        public static final String COLUMN_SODIUM_MG_QUANTITY = "sodiumMg";
        public static final String COLUMN_FOOD_ID = "foodId";
        public static final String COLUMN_DATETIME = "datetime";

    }

    public static final String createTableDailyFoods(){

        return "CREATE TABLE " + DailyFood.TABLE_NAME + " (" +
                DailyFood._ID + " INTEGER PRIMARY KEY," +
                DailyFood.COLUMN_FOOD_QUANTITY + REAL_TYPE + COMMA_SEP +
                DailyFood.COLUMN_SODIUM_MG_QUANTITY + REAL_TYPE + COMMA_SEP +
                DailyFood.COLUMN_DATETIME + INTEGER_TYPE + COMMA_SEP +
                DailyFood.COLUMN_FOOD_ID + TEXT_TYPE + COMMA_SEP +
                "FOREIGN KEY(" + DailyFood.COLUMN_FOOD_ID + ") REFERENCES "
                +Food.TABLE_NAME + "(" + Food._ID + ")"+" )";
    }

    public static final String deleteTableDailyFoods(){
        return "DROP TABLE IF EXISTS " + DailyFood.TABLE_NAME;
    }
}

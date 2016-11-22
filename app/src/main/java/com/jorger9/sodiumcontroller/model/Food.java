package com.jorger9.sodiumcontroller.model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jorger9 on 11/22/16.
 */

public class Food extends RealmObject {

    @PrimaryKey
    private long id;
    private String foodName;
    private double sodiumMg;
    private FoodGroup foodGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getSodiumMg() {
        return sodiumMg;
    }

    public void setSodiumMg(double sodiumMg) {
        this.sodiumMg = sodiumMg;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public void loadData()
    {
        write(1,"Tortillas de maíz blanco",0.60,3);
        write(2,"Hojuelas de maíz",1238.00,3);
        write(3,"Hojuelas de maíz azucaradas",649.00,3);
        write(4,"Chips de maíz",6.73,3);
        write(5,"Palomitas de maíz, con grasa y sal",1.82,3);
        write(6,"Palomitas de maíz con caramelo",3.69,3);
        write(7,"Palomitas de maíz sabor queso",11.82,3);
        write(8,"Totopos",6.70,3);
        write(9,"Salvado de trigo",52.00,3);
        write(10,"Germen de trigo",26.00,3);

        write(11,"Frijo bayo gordo",25.00,6);
        write(12,"Frijol negro",12.00,6);
        write(13,"Garbanzo",26.00,6);
        write(14,"Haba seca",13.00,6);
        write(15,"Lentejas",10.00,6);
        write(16,"Soya (semillas cocidas)",2.00,6);
        write(17,"Alubia (promedio)",18.00,6);

        write(18,"Ajonjolí",60.0,5);
        write(19,"Almendra",4.00,5);
        write(20,"Avellana",2.00,5);
        write(21,"Cacahuate tostado",5.00,5);
        write(22,"Cacahuate tostado con sal",478.00,5);
        write(23,"Castaña cruda",6.00,5);
        write(24,"Girasol (semilla pelada)",30.00,5);
        write(25,"Nuez de Castilla",2.00,5);
        write(26,"Pistache sin cáscara",426.80,5);
        write(27,"Semilla de calabaza",0.82,5);

        write(28,"Acelga",0.65,1);
        write(29,"Ajo",19.00,1);
        write(30,"Alcachofa",80.00,1);
        write(31,"Apio",88.00,1);
        write(32,"Berenjena",4.00,1);
        write(33,"Berro",13.00,1);
        write(34,"Betabel",57.00,1);
        write(35,"Brócoli",42.00,1);
        write(36,"Calabacita italiana",65.00,1);
        write(37,"Calabaza criolla (verde)",72.00,1);

        write(38,"Arándano rojo",1.00,2);
        write(39,"Ciruela cereza (roja)",0.04,2);
        write(40,"Ciruela amarilla",1.00,2);
        write(41,"Coco (agua de)",25.00,2);
        write(42,"Durazno amarillo",1.00,2);
        write(43,"Frambruesa",1.00,2);
        write(44,"Fresa",1.00,2);
        write(45,"Granada roja",3.00,2);
        write(46,"Guanábana",3.00,2);
        write(47,"Guayaba",3.00,2);

        write(48,"Guajolote o pavo",65.00,4);
        write(49,"Hígado de pollo",79.00,4);
        write(50,"Pato de crianza",59.00,4);
        write(51,"Pollo (pechuga con piel)",65.00,4);
        write(52,"Pollo (pierna con piel)",73.00,4);
        write(53,"Pollo (promedio)",70.00,4);
        write(54,"Carne grasa de borrego",75.00,4);
        write(55,"Carne de borrego magra con hueso",75.00,4);
        write(56,"Carne de borrego magra sin hueso",75.00,4);
        write(57,"Carne de res magra",63.00,4);

        write(58,"Leche entera en polvo pasteurizada",371.00,7);
        write(59,"Leche entera pasteurizada",125.00,7);
        write(60,"Leche parcialmente descremada",50.00,7);
        write(61,"Leche semidescremada ultrapasteurizada",128.33,7);
        write(62,"Leche parcialmente descremada ultrapasteurizada con ácido fólico",130.00,7);
        write(63,"Leche descremada ultrapasteurizada",125.00,7);
        write(64,"Leche evaporada (entera)",282.00,7);
        write(65,"Leche evaporada (descremada)",310.00,7);
        write(66,"Leche fresca de vaca (pasteurizada o cruda)",103.00,7);
        write(67,"Leche condensada, azucarada",410.00,7);

        write(68,"Aceite de maíz",0.00,8);
        write(69,"Aceite de oliva",0.00,8);
        write(70,"Aceite de soya",0.00,8);
        write(71,"Manteca de cerdo",0.00,8);
        write(72,"Mantequilla con sal",893.33,8);
        write(73,"Mantequilla sin sal",10.00,8);
        write(74,"Margarina con sal",1020.00,8);

    }

    private void write(int id, String name, double mg,long groupId){
        final Food food = new Food();
        food.setId(id);
        food.setFoodName(name);
        food.setSodiumMg(mg);

        Realm realm = Realm.getDefaultInstance();

        if(!realm.isInTransaction()) realm.beginTransaction();

       FoodGroup foodGroup = realm.where(FoodGroup.class).equalTo("id",groupId).findFirst();

        food.setFoodGroup(foodGroup);

        realm.copyToRealmOrUpdate(food);

        realm.commitTransaction();

    }
}

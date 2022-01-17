package com.example.karenscars;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {
    public static Context Context;

    public static void StoreData(List<Car> cars)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(cars );
        editor.putString("cars", json);
        editor.commit();
    }

    public static List<Car> LoadData()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        String json = sp.getString("cars",null);
        if (json != null)
        {
            Type type = new TypeToken<List<Car>>(){}.getType();
            return new Gson().fromJson(json,type);
        }
        else
        {
            List<Car> cars = new ArrayList<Car>();
            cars.add(new Car(R.drawable.car1,"Tesla","5","2045","40,000"));
            cars.add(new Car(R.drawable.car2,"Jeep","wrangler","1960","70,000"));
            cars.add(new Car(R.drawable.car3,"Ferarri","idk","2028","200,000"));
            cars.add(new Car(R.drawable.car4,"Toyota","prius","2011","10,000"));
            cars.add(new Car(R.drawable.car5,"Mazda","cx5","2022","60,000"));
            cars.add(new Car(R.drawable.car6,"Nisan","sentra","2010","20,000"));
            cars.add(new Car(R.drawable.car7,"Ford","explorer","2017","25,000"));
            cars.add(new Car(R.drawable.car8,"Suburu","awesome","2021","30,000"));
            return cars;
        }
    }
}
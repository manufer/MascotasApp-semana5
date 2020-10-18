package com.example.petsapp.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.petsapp.pojo.Pets;

import java.util.ArrayList;
import java.util.Collections;

public class PetsConstructor {

    private final DataBase dataBase;

    public PetsConstructor(Context context) {
        dataBase = new DataBase(context);
    }

    public ArrayList<Pets> getPets(){
        return dataBase.getPets();
    }
    public int getLikes(Pets pet){
        return dataBase.getLikes(pet);
    }
    public void insertLike(Pets pet){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_LIKES_AMOUNT,pet.getLikes());
        dataBase.insertLike(contentValues,pet);
    }
    public ArrayList<Pets> getTopPets(){
        ArrayList<Pets> pets = new ArrayList<>(getPets());
        ArrayList<Pets> topPets = new ArrayList<>(pets);
        Collections.sort(topPets, Collections.<Pets>reverseOrder());

        if (pets.size() > 5)
            topPets.subList(5, pets.size()).clear();
        return topPets;
    }
}
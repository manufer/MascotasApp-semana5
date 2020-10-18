package com.example.petsapp.presenter;

import android.content.Context;

import com.example.petsapp.activity.TopPets;
import com.example.petsapp.db.PetsConstructor;
import com.example.petsapp.fragment.iHomeFragmentView;
import com.example.petsapp.pojo.Pets;

import java.util.ArrayList;

public class HomeFragmentPresenter implements iHomeFragmentPresenter{
    private final iHomeFragmentView iHomeFragmentView;
    private final PetsConstructor petsConstructor;
    private ArrayList<Pets> pets;
    private ArrayList<Pets> topPets;
    private final String className;

    public HomeFragmentPresenter(iHomeFragmentView iHomeFragmentView, Context context, String className) {
        this.iHomeFragmentView = iHomeFragmentView;
        petsConstructor = new PetsConstructor(context);
        this.className = className;
        getPets();
    }

    @Override
    public void getPets() {
        pets = petsConstructor.getPets();
        topPets = petsConstructor.getTopPets();
        showPetsRv();
    }

    @Override
    public void showPetsRv() {
        if(className.equals(TopPets.class.getName()))
            iHomeFragmentView.initializeRecyclerViewAdapter(iHomeFragmentView.createAdapter(topPets));
        else
            iHomeFragmentView.initializeRecyclerViewAdapter(iHomeFragmentView.createAdapter(pets));
        iHomeFragmentView.generateVerticalLinearLayout();
    }

}

package com.example.petsapp.fragment;

import com.example.petsapp.adapter.PetAdapter;
import com.example.petsapp.pojo.Pets;

import java.util.ArrayList;

public interface iHomeFragmentView {
    void generateVerticalLinearLayout();
    PetAdapter createAdapter(ArrayList<Pets> pets);
    void initializeRecyclerViewAdapter(PetAdapter adapter);
}

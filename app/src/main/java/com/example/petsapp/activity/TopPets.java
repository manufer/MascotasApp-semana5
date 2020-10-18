package com.example.petsapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.petsapp.R;
import com.example.petsapp.adapter.PetAdapter;
import com.example.petsapp.fragment.iHomeFragmentView;
import com.example.petsapp.pojo.Pets;
import com.example.petsapp.presenter.HomeFragmentPresenter;

import java.util.ArrayList;
import java.util.Objects;

public class TopPets extends AppCompatActivity implements iHomeFragmentView {
    RecyclerView rvTopPets;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_pets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Top pets");

        this.setTitle(getString(R.string.top_pets_activity));

        rvTopPets = findViewById(R.id.rvTopPets);
        new HomeFragmentPresenter(this,getBaseContext(),getClass().getName());
    }

    @Override
    public void generateVerticalLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTopPets.setLayoutManager(linearLayoutManager);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pets> pets) {
        return new PetAdapter(pets,this,new Fragment());
    }

    @Override
    public void initializeRecyclerViewAdapter(PetAdapter adapter) {
        rvTopPets.setAdapter(adapter);
    }
}
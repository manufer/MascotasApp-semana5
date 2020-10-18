package com.example.petsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petsapp.adapter.PetAdapter;
import com.example.petsapp.pojo.Pets;
import com.example.petsapp.R;
import com.example.petsapp.presenter.HomeFragmentPresenter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements iHomeFragmentView {

    private RecyclerView rvPets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvPets = view.findViewById(R.id.rvPets);

        new HomeFragmentPresenter(this, getContext(), "");
        return view;
    }

    @Override
    public void generateVerticalLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        rvPets.setLayoutManager(linearLayoutManager);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pets> pets) {
        return new PetAdapter(pets,getActivity(),this);
    }

    @Override
    public void initializeRecyclerViewAdapter(PetAdapter adapter) {
        rvPets.setAdapter(adapter);
    }
}
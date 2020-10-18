package com.example.petsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petsapp.R;
import com.example.petsapp.adapter.PetAdapter;
import com.example.petsapp.pojo.Pets;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    RecyclerView rvMyPics;
    ArrayList<Pets> pics = new ArrayList<>();

    public ProfileFragment() {
        addPics();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        rvMyPics = view.findViewById(R.id.rvMyPics);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        rvMyPics.setLayoutManager(gridLayoutManager);
        rvMyPics.setAdapter(new PetAdapter(pics,getActivity(),this));
        return view;
    }
    public void addPics(){
        pics.add(new Pets(R.drawable.dog2,"",1));
        pics.add(new Pets(R.drawable.dog3,"",5));
        pics.add(new Pets(R.drawable.dog4,"",10));
        pics.add(new Pets(R.drawable.dog5,"",15));
        pics.add(new Pets(R.drawable.dog6,"",3));
        pics.add(new Pets(R.drawable.dog,"",6));
        pics.add(new Pets(R.drawable.dog7,"",9));
        pics.add(new Pets(R.drawable.dog8,"",12));
    }
}
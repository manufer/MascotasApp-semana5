package com.example.petsapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petsapp.activity.MainActivity;
import com.example.petsapp.db.PetsConstructor;
import com.example.petsapp.fragment.HomeFragment;
import com.example.petsapp.fragment.ProfileFragment;
import com.example.petsapp.pojo.Pets;
import com.example.petsapp.R;
import com.example.petsapp.activity.TopPets;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolderPet>{
    ArrayList <Pets> pets;
    Activity activity;
    Fragment fragment;

    public PetAdapter(ArrayList<Pets> pets,Activity activity, Fragment fragment) {
        this.pets = pets;
        this.activity = activity;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolderPet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_cardview,parent,false);
        return new ViewHolderPet(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderPet holder, int position) {
        final Pets pet = pets.get(position);
        final PetsConstructor petsConstructor = new PetsConstructor(activity);

        holder.imgPet.setImageResource(pet.getImgPet());
        holder.tvName.setText(pet.getName());

        if(activity.getClass().getName().equals(MainActivity.class.getName())){

            if(fragment.getClass().getName().equals(HomeFragment.class.getName())){
                pet.setLikes(petsConstructor.getLikes(pet));
                holder.tvLikes.setText(String.valueOf(pet.getLikes()));
                activity.registerForContextMenu(holder.imgPet);
                holder.tvName.setWidth(700);

                holder.btnLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pet.setLikes(pet.getLikes()+1);
                        petsConstructor.insertLike(pet);
                        holder.tvLikes.setText(String.valueOf(pet.getLikes()));
                    }
                });

            }else if(fragment.getClass().getName().equals(ProfileFragment.class.getName())){
                holder.tvLikes.setText(String.valueOf(pet.getLikes()));
                holder.btnLike.setVisibility(View.INVISIBLE);
            }


        }else if(activity.getClass().getName().equals(TopPets.class.getName())){
            holder.tvLikes.setText(String.valueOf(pet.getLikes()));
            holder.btnLike.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ViewHolderPet extends RecyclerView.ViewHolder{
        private final ImageView imgPet;
        private final TextView tvName;
        private final TextView tvLikes;
        private final ImageButton btnLike;

        public ViewHolderPet(@NonNull View itemView) {
            super(itemView);
            imgPet = itemView.findViewById(R.id.imgPet);
            tvName = itemView.findViewById(R.id.tvName);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            btnLike = itemView.findViewById(R.id.btnLike);
        }
    }
}
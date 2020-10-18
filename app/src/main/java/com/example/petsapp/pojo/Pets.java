package com.example.petsapp.pojo;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Pets implements Comparable<Pets>, Parcelable {
    private int imgPet;
    private String name;
    private int likes;
    private  int id;

    public Pets(int imgPet, String name, int likes) {
        this.imgPet = imgPet;
        this.name = name;
        this.likes = likes;
    }

    public Pets() {}

    public int getImgPet() {
        return imgPet;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(Pets pets) {
        return Integer.compare(likes, pets.likes);
    }

    protected Pets(Parcel in) {
        imgPet = in.readInt();
        name = in.readString();
        likes = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgPet);
        dest.writeString(name);
        dest.writeInt(likes);
    }

    public static final Parcelable.Creator<Pets> CREATOR = new Parcelable.Creator<Pets>() {
        @Override
        public Pets createFromParcel(Parcel in) {
            return new Pets(in);
        }

        @Override
        public Pets[] newArray(int size) {
            return new Pets[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setImgPet(int imgPet) {
        this.imgPet = imgPet;
    }

    public void setName(String name) {
        this.name = name;
    }
}
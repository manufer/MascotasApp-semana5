package com.example.petsapp.db;

public final class DataBaseConstants {
    public static final String DATABASE_NAME = "pets";
    public static final int  DATABASE_VERSION = 1;

    public static final String TABLE_PETS = "pet";
    public static final String TABLE_PETS_ID = "id";
    public static final String TABLE_PETS_NAME = "name";
    public static final String TABLE_PETS_IMG = "img";

    public static final String TABLE_LIKES = "pet_likes";
    public static final String TABLE_LIKES_ID = "id";
    public static final String TABLE_LIKES_ID_PET = "id_pet";
    public static final String TABLE_LIKES_AMOUNT = "total_likes";
}

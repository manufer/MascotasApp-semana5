package com.example.petsapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petsapp.R;
import com.example.petsapp.pojo.Pets;

import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreatePetTable = "CREATE TABLE " + DataBaseConstants.TABLE_PETS + "("+
                DataBaseConstants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_PETS_NAME + " TEXT, " +
                DataBaseConstants.TABLE_PETS_IMG + " INTEGER" +
                ")";
        String queryCreatePetLikesTable = "CREATE TABLE " + DataBaseConstants.TABLE_LIKES + "(" +
                DataBaseConstants.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_LIKES_ID_PET + " INTEGER, " +
                DataBaseConstants.TABLE_LIKES_AMOUNT + " INTEGER, " +
                "FOREIGN KEY (" + DataBaseConstants.TABLE_LIKES_ID_PET + ") " +
                "REFERENCES " + DataBaseConstants.TABLE_PETS + "(" + DataBaseConstants.TABLE_PETS_ID + ")" +
                ")";

        sqLiteDatabase.execSQL(queryCreatePetTable);
        sqLiteDatabase.execSQL(queryCreatePetLikesTable);

        insertPets(sqLiteDatabase);
        setDefaultLikes(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_PETS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_LIKES);

        onCreate(sqLiteDatabase);
    }

    public void insertPets(SQLiteDatabase sqLiteDatabase){
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Tiger",DataBaseConstants.TABLE_PETS_IMG, R.drawable.tiger);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Cat",DataBaseConstants.TABLE_PETS_IMG, R.drawable.cat);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Lion",DataBaseConstants.TABLE_PETS_IMG, R.drawable.lion);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Rabbit",DataBaseConstants.TABLE_PETS_IMG, R.drawable.rabbit);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Panda",DataBaseConstants.TABLE_PETS_IMG, R.drawable.panda);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Hamster",DataBaseConstants.TABLE_PETS_IMG, R.drawable.hamster);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Chicken",DataBaseConstants.TABLE_PETS_IMG, R.drawable.chicken);
        insertPet(sqLiteDatabase,new ContentValues(),DataBaseConstants.TABLE_PETS_NAME,"Dog",DataBaseConstants.TABLE_PETS_IMG, R.drawable.dog);
    }
    public void insertPet(SQLiteDatabase sqLiteDatabase,ContentValues contentValues, String nameField, String nameValue, String imgField, int imgValue){
        contentValues.put(nameField,nameValue);
        contentValues.put(imgField, imgValue);
        sqLiteDatabase.insert(DataBaseConstants.TABLE_PETS,null,contentValues);
    }
    public void setDefaultLikes(SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        String query = "SELECT "+DataBaseConstants.TABLE_PETS_ID+" FROM " + DataBaseConstants.TABLE_PETS;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext()){
            contentValues.put(DataBaseConstants.TABLE_LIKES_ID_PET,cursor.getInt(0));
            contentValues.put(DataBaseConstants.TABLE_LIKES_AMOUNT,0);
            sqLiteDatabase.insert(DataBaseConstants.TABLE_LIKES,null,contentValues);
        }

        cursor.close();
    }
    public void insertLike(ContentValues contentValues, Pets pet){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(DataBaseConstants.TABLE_LIKES,contentValues,DataBaseConstants.TABLE_LIKES_ID_PET+"="+pet.getId(),null);
        sqLiteDatabase.close();
    }
    public ArrayList<Pets> getPets(){
        ArrayList<Pets> pets = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext()){
            Pets myPet = new Pets();
            myPet.setId(cursor.getInt(0));
            myPet.setName(cursor.getString(1));
            myPet.setImgPet(cursor.getInt(2));

            pets.add(myPet);
        }

        query = "SELECT " + DataBaseConstants.TABLE_LIKES_AMOUNT + "," +DataBaseConstants.TABLE_LIKES_ID_PET + " FROM " + DataBaseConstants.TABLE_LIKES;
        cursor = sqLiteDatabase.rawQuery(query,null);

        while(cursor.moveToNext())
            for(int i = 0 ; i < pets.size();i++)
                if(pets.get(i).getId() == cursor.getInt(1))
                    pets.get(i).setLikes(cursor.getInt(0));

        cursor.close();
        sqLiteDatabase.close();
        return pets;
    }
    public int getLikes(Pets pet){
        int likes = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT " + DataBaseConstants.TABLE_LIKES_AMOUNT +
                " FROM " + DataBaseConstants.TABLE_LIKES +
                " WHERE " + DataBaseConstants.TABLE_LIKES_ID_PET + "="+pet.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToNext())
            likes = cursor.getInt(0);

        sqLiteDatabase.close();
        cursor.close();
        return likes;
    }
}
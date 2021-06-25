package com.example.foodcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String ID = "ID";
    public static final String IMAGE_ID = "IMAGE_" + ID;
    public static final String FOOD_NAME = "FOOD_NAME";
    public static final String RESTAURANT_NAME = "RESTAURANT_NAME";
    public static final String PRICE = "PRICE";
    public static final String QUANTITY = "QUANTITY";
    public static final String TABLE_NAME = "FOOD_CART";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IMAGE_ID + " INTEGER, " + FOOD_NAME + " TEXT, " + RESTAURANT_NAME + " TEXT, " + PRICE + " INTEGER, " + QUANTITY + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    boolean onAdd(food_item curr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_ID,  curr.getImageId());
        cv.put(FOOD_NAME, curr.getItemName());
        cv.put(RESTAURANT_NAME, curr.getRestaurantName());
        cv.put(PRICE, curr.getPrice());
        cv.put(QUANTITY, curr.getQuantity());

        long row = db.insert(TABLE_NAME, null, cv);
        if(row == -1){
            return false;
        }else{
            return true;
        }
    }
    ArrayList<food_item>getCartList(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<food_item>CartList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                int imageId = cursor.getInt(1);
                String foodName = cursor.getString(2);
                int quantity = cursor.getInt(5);
                int price = cursor.getInt(4);
                int cost = quantity * price;
                int id = cursor.getInt(0);
                food_item newItem = new food_item(id, imageId, foodName, price,  cost, quantity);
                CartList.add(newItem);
            }while ((cursor.moveToNext()));
        }
        db.close();
        return CartList;
    }

    boolean updateOne(food_item curr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_ID,  curr.getImageId());
        cv.put(FOOD_NAME, curr.getItemName());
        cv.put(RESTAURANT_NAME, curr.getRestaurantName());
        cv.put(PRICE, curr.getPrice());
        cv.put(QUANTITY, curr.getQuantity());
        int result = db.update(TABLE_NAME, cv, ID + " =?", new String[]{String.valueOf(curr.getId())});
        db.close();
        return result > 0;
    }

    void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?", new String[]{"" + id});
        db.close();
    }

    void CompleteDelete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();

    }

}

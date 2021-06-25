package com.example.foodcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class itemUpdate extends AppCompatActivity {

    ImageView image;
    TextView itemName, quantityView;
    Button inc, dec, update, delete;
    int Quantity = 1, curr_food_imageId, curr_food_price, curr_food_id;
    String curr_food_name, curr_food_description, curr_food_restaurant_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);
        getSupportActionBar().hide();
        curr_food_id = getIntent().getIntExtra("id", 0);
        curr_food_name = getIntent().getStringExtra("food_name");
        curr_food_description = getIntent().getStringExtra("food_description");
        curr_food_imageId = getIntent().getIntExtra("food_image", 1);
        curr_food_price = getIntent().getIntExtra("food_price", 0);
        curr_food_restaurant_name = getIntent().getStringExtra("food_restaurant_name");

        image = findViewById(R.id.Uimage);
        itemName = findViewById(R.id.Uitem);
        quantityView = findViewById(R.id.Uquantity);
        inc = findViewById(R.id.uBtnInc);
        dec = findViewById(R.id.uBtnDec);
        delete = findViewById(R.id.uBtnDelete);
        update = findViewById(R.id.btnUpdate);

        image.setImageResource(curr_food_imageId);
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inc();
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dec();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food_item food = new food_item(curr_food_id, curr_food_restaurant_name, curr_food_name, curr_food_description, curr_food_price, curr_food_imageId, Quantity);
                DataBaseHelper db = new DataBaseHelper(itemUpdate.this);
                boolean success = db.updateOne(food);

                if(success){
                    Toast.makeText(itemUpdate.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(itemUpdate.this, "Item Not Found", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db = new DataBaseHelper(itemUpdate.this);
                db.delete(curr_food_id);
                finish();
            }
        });
    }


    void Display(){
        quantityView.setText("" + Quantity);
    }
    void inc(){
        if(Quantity == 20){
            Toast.makeText(itemUpdate.this, "Maximum Selected", Toast.LENGTH_SHORT).show();
        }else{
            Quantity++;
            Display();
        }
    }
    void dec(){
        if(Quantity == 1){
            Toast.makeText(itemUpdate.this, "Minimum Selected", Toast.LENGTH_SHORT).show();
        }else{
            Quantity--;
            Display();
        }
    }
}
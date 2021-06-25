package com.example.foodcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartView extends AppCompatActivity {

    TextView Name, quantity, price, cost;
    int image;
    DataBaseHelper db;
    ListView lv;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        Name = findViewById(R.id.Citem);
        quantity = findViewById(R.id.Cquantity);
        price = findViewById(R.id.Cprice);
        cost = findViewById(R.id.Ccost);
        lv = findViewById(R.id.Clv);
        db = new DataBaseHelper(CartView.this);


        Display();
        ArrayList<food_item>itemList = db.getCartList();
        cartAdapter = new CartAdapter(CartView.this, itemList);
        lv.setAdapter(cartAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CartView.this, itemUpdate.class);
                food_item food = (food_item) parent.getItemAtPosition(position);
                intent.putExtra("id", food.getId());
                intent.putExtra("food_name", food.getItemName());
                intent.putExtra("food_description", food.getDescription());
                intent.putExtra("food_image", food.getImageId());
                intent.putExtra("food_price", food.getPrice());
                intent.putExtra("food_restaurant_name", food.getRestaurantName());
                startActivity(intent);
            }
        });
    }
    void Display(){
        ArrayList<food_item>itemList = db.getCartList();
        cartAdapter = new CartAdapter(CartView.this, itemList);
        lv.setAdapter(cartAdapter);
    }

    @Override
    protected void onPostResume() {
        Display();
        super.onPostResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                DataBaseHelper db = new DataBaseHelper(CartView.this);
                db.CompleteDelete();
                Display();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
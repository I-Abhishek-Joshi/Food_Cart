package com.example.foodcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.list);
        ArrayList<food_item>foodList = new ArrayList<>();
        foodList.add(new food_item(-1, "Taste of Lucknow", "French Fries", "Fresh and Delicious French Fries", 130, R.drawable.ic_french_fries));
        foodList.add(new food_item(-1, "Go 69 Pizza", "Pizza", "1 Double topping pizza + Garlic Bread Stick + Dip + 250ml Cold drink", 249, R.drawable.ic__311166146));
        foodList.add(new food_item(-1, "Taste of Lucknow", "French Fries", "Fresh and Delicious French Fries", 130, R.drawable.ic_french_fries));
        foodList.add(new food_item(-1, "Go 69 Pizza", "Pizza", "1 Double topping pizza + Garlic Bread Stick + Dip + 250ml Cold drink", 249, R.drawable.ic__311166146));
        foodList.add(new food_item(-1, "Taste of Lucknow", "French Fries", "Fresh and Delicious French Fries", 130, R.drawable.ic_french_fries));
        foodList.add(new food_item(-1, "Go 69 Pizza", "Pizza", "1 Double topping pizza + Garlic Bread Stick + Dip + 250ml Cold drink", 249, R.drawable.ic__311166146));
        foodList.add(new food_item(-1, "Taste of Lucknow", "French Fries", "Fresh and Delicious French Fries", 130, R.drawable.ic_french_fries));
        foodList.add(new food_item(-1, "Go 69 Pizza", "Pizza", "1 Double topping pizza + Garlic Bread Stick + Dip + 250ml Cold drink", 249, R.drawable.ic__311166146));
        foodList.add(new food_item(-1, "Taste of Lucknow", "French Fries", "Fresh and Delicious French Fries", 130, R.drawable.ic_french_fries));

        CustomAdapter adapter = new CustomAdapter(MainActivity.this, foodList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, editerActivity.class);
                food_item food = (food_item) parent.getItemAtPosition(position);
                intent.putExtra("food_name", food.getItemName());
                intent.putExtra("food_description", food.getDescription());
                intent.putExtra("food_image", food.getImageId());
                intent.putExtra("food_price", food.getPrice());
                intent.putExtra("food_restaurant_name", food.getRestaurantName());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                DataBaseHelper db = new DataBaseHelper(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, CartView.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
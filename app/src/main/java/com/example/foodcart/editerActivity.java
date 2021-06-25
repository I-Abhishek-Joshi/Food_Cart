package com.example.foodcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class editerActivity extends AppCompatActivity {

    ImageView eImage;
    Button inc, dec, addCart, order;
    TextView eItemName, eDescription, eQuantity;
    EditText name, phoneNo;
    int Quantity = 1, curr_food_price, curr_food_imageId;
    String curr_food_name, curr_food_description, curr_food_restaurant_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer);
        getSupportActionBar().hide();
        curr_food_name = getIntent().getStringExtra("food_name");
        curr_food_description = getIntent().getStringExtra("food_description");
        curr_food_imageId = getIntent().getIntExtra("food_image", 1);
        curr_food_price = getIntent().getIntExtra("food_price", 0);
        curr_food_restaurant_name = getIntent().getStringExtra("food_restaurant_name");

        inc = findViewById(R.id.eBtnInc);
        dec = findViewById(R.id.eBtnDesc);
        addCart = findViewById(R.id.eBtnCart);
        order = findViewById(R.id.eBtnOrder);

        eImage = findViewById(R.id.eImage);
        eItemName = findViewById(R.id.eItemName);
        eDescription = findViewById(R.id.eDescription);
        eQuantity = findViewById(R.id.eQuantity);

        name = findViewById(R.id.eName);
        phoneNo = findViewById(R.id.ePhone);

        eImage.setImageResource(curr_food_imageId);
        eItemName.setText(curr_food_name);
        eDescription.setText(curr_food_description);
        eQuantity.setText("1");

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
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cname = name.getText().toString().trim();
                String PhoneNo = phoneNo.getText().toString().trim();

                if(Cname.length() == 0 || PhoneNo.length() != 10){
                    Toast.makeText(editerActivity.this, "Enter Complete Details", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        food_item newFood = new food_item(-1, curr_food_restaurant_name, curr_food_name, curr_food_description, curr_food_price, curr_food_imageId, Quantity);
                        DataBaseHelper db = new DataBaseHelper(editerActivity.this);
                        boolean success = db.onAdd(newFood);
                        if(success){
                            Toast.makeText(editerActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(editerActivity.this, "Error Adding Item", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(editerActivity.this, "Error Creating Food Item", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cname = name.getText().toString().trim();
                String PhoneNo = phoneNo.getText().toString().trim();

                if(Cname.length() == 0 || PhoneNo.length() != 10){
                    Toast.makeText(editerActivity.this, "Enter Complete Details", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(editerActivity.this, OrderActivity.class);
                    intent.putExtra("Name", Cname);
                    intent.putExtra("Phone", PhoneNo);
                    intent.putExtra("food", curr_food_name);
                    intent.putExtra("Cost", curr_food_price * Quantity);
                    startActivity(intent);
                }
            }
        });

    }

    void Display(){
        eQuantity.setText("" + Quantity);
    }
    void inc(){
        if(Quantity == 20){
            Toast.makeText(editerActivity.this, "Maximum Selected", Toast.LENGTH_SHORT).show();
        }else{
            Quantity++;
            Display();
        }
    }
    void dec(){
        if(Quantity == 1){
            Toast.makeText(editerActivity.this, "Minimum Selected", Toast.LENGTH_SHORT).show();
        }else{
            Quantity--;
            Display();
        }
    }

}
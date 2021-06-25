package com.example.foodcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView Name, PhoneNo, Item, Cost;
    String cName;
    String cPhone;
    String cItem;
    int cCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Name = findViewById(R.id.Oname);
        PhoneNo = findViewById(R.id.Ophone);
        Item = findViewById(R.id.Oitem);
        Cost = findViewById(R.id.OCost);
        cName = getIntent().getStringExtra("Name");
        cPhone = getIntent().getStringExtra("Phone");
        cItem = getIntent().getStringExtra("food");
        cCost = getIntent().getIntExtra("Cost", 0);

        Name.setText(cName);
        PhoneNo.setText(cPhone);
        Item.setText(cItem);
        Cost.setText(String.valueOf(cCost));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
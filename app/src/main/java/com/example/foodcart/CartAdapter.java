package com.example.foodcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CartAdapter extends ArrayAdapter<food_item> {
    public CartAdapter(@NonNull Context context, @NonNull List<food_item> foodList) {
        super(context, 0, foodList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View myView = convertView;
        if(myView == null){
            myView = LayoutInflater.from(getContext()).inflate(R.layout.cart_view, parent, false);
        }

        food_item curr = getItem(position);
        TextView food_name = myView.findViewById(R.id.Citem);
        ImageView imageView = myView.findViewById(R.id.Cimage);
        TextView Quantity = myView.findViewById(R.id.Cquantity);
        TextView cPrice = myView.findViewById(R.id.Cprice);
        TextView cCost = myView.findViewById(R.id.Ccost);
        food_name.setText(curr.getItemName());
        imageView.setImageResource(curr.getImageId());
        Quantity.setText(String.valueOf(curr.getQuantity()));
        cPrice.setText(String.valueOf(curr.getPrice()));
        cCost.setText(String.valueOf(curr.getCost()));

        return  myView;
    }
}

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

public class CustomAdapter extends ArrayAdapter<food_item> {
    public CustomAdapter(@NonNull Context context, @NonNull List<food_item> foodList) {
        super(context, 0, foodList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View myView = convertView;
        if(myView == null){
            myView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        food_item curr = getItem(position);
        TextView food_name = myView.findViewById(R.id.food_name);
        TextView restaurant_name = myView.findViewById(R.id.restaurant_name);
        ImageView imageView = myView.findViewById(R.id.imageView);
        food_name.setText(curr.getItemName());
        restaurant_name.setText(curr.getRestaurantName());
        imageView.setImageResource(curr.getImageId());

        return myView;
    }
}

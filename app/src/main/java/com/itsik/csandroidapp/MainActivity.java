package com.itsik.csandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCoupon;
    private ArrayList<Coupon> food = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewCoupon = (ListView)findViewById(R.id.listViewCoupon);

        food.add(new Coupon("Bread", R.drawable.bread));
        food.add(new Coupon("Cheese", R.drawable.cheese));
        food.add(new Coupon("Ice Cream", R.drawable.icecream));
        food.add(new Coupon("Pizza", R.drawable.pizza));
        food.add(new Coupon("Sushi", R.drawable.sushi));

        CouponsAdapter foodAdapter = new CouponsAdapter()(this, food);

        listViewCoupon.setAdapter(foodAdapter);

    }
}

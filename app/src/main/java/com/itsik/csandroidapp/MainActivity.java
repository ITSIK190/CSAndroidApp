package com.itsik.csandroidapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {


    private ArrayList<Coupon> coupons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0;i<100;i++){
            Coupon c = new Coupon("Coupon.jpg", "Coupon Title", "6/8/2017", "9/9/2017", 10.5);
            coupons.add(c);
        }
        CouponsAdapter adapter = new CouponsAdapter(this, coupons);
        setListAdapter(adapter);

    }
}

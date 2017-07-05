package com.itsik.csandroidapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements TextDownloader.Callbacks {


    private ArrayList<Coupon> coupons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextDownloader textDownloader = new TextDownloader(this);
        textDownloader.execute("http://jsonplaceholder.typicode.com/photos");

    }

    public void buyCoupon(View view) {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAboutToBegin() {

    }

    @Override
    public void onSuccess(String downloadedText) {
        try {
            JSONArray jsonArray = new JSONArray(downloadedText);
            for (int i =0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");

                String thumbNailUrl = jsonObject.getString("thumbnailUrl");

                Coupon coupon = new Coupon("","" , thumbNailUrl, title, 0.0);
                coupons.add(coupon);
            }
            CouponsAdapter adapter = new CouponsAdapter(this, coupons);
            setListAdapter(adapter);
        } catch (JSONException e) {
            Toast.makeText(this, "error: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onError(int httpStatusCode, String errorMessage) {
        Toast.makeText(this, "error: "+ errorMessage, Toast.LENGTH_LONG).show();
    }
}

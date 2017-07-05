package com.itsik.csandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements TextDownloader.Callbacks{

    EditText editTextUserName;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUserName = (EditText)findViewById(R.id.EditTextUserName);
        editTextPassword = (EditText)findViewById(R.id.EditTextPassword);
    }

    public void ButtonLoginOnClick(View view) {
        String un = editTextUserName.getText().toString();
        String pw = editTextPassword.getText().toString();

        TextDownloader textDownloader = new TextDownloader(this);
        textDownloader.execute("http://10.0.2.2:8080/CouponWeb/rest/Customer/getAllPurchasedCouponsService?User=" + un+"&PW=" + pw);

        if (un.equals("abcd")&& pw.equals("1234")){
            Credentials.userName=un;
            Credentials.password=pw;
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this,"fuck off", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAboutToBegin() {

    }

    @Override
    public void onSuccess(String downloadedText) {

        try {
            coupons=new ArrayList<>();
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

    }
}

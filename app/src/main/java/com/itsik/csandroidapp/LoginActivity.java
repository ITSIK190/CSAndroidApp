package com.itsik.csandroidapp;

import android.app.ListActivity;
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

public class LoginActivity extends AppCompatActivity  implements TextDownloader.Callbacks{

    EditText editTextUserName;
    EditText editTextPassword;
    private ArrayList<Coupon> coupons;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUserName = (EditText)findViewById(R.id.EditTextUserName);
        editTextPassword = (EditText)findViewById(R.id.EditTextPassword);
    }

    public void ButtonLoginOnClick(View view) {
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        TextDownloader textDownloader = new TextDownloader(this);
        textDownloader.execute("http://10.0.2.2:8080/CouponWeb/rest/Customer/loginForAndroid?User=" + userName+"&PW=" + password);


    }

    @Override
    public void onAboutToBegin() {

    }

    @Override
    public void onSuccess(String downloadedText) {

        if (downloadedText.contains("success")){

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this,"Wrong username or password, please try again..", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onError(int httpStatusCode, String errorMessage) {
        Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
    }
}

package com.itsik.csandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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
}

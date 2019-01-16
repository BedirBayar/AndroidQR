package com.example.bedir.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UrlActivity extends AppCompatActivity {
    EditText UrlEdit;
    Button UrlEnter;
    DatabaseHelper db;
    String TheUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        UrlEdit=findViewById(R.id.enter_url_edittext);
        UrlEnter=findViewById(R.id.btn_enter_url);
        db= new DatabaseHelper(getApplicationContext());

        UrlEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheUrl=UrlEdit.getText().toString();
                db.insertUrl(TheUrl);
                Intent QrShowScreen = new Intent(UrlActivity.this, UrlShowActivity.class);
                QrShowScreen.putExtra("urlsender", TheUrl);
                startActivity(QrShowScreen);
            }
        });
    }
}

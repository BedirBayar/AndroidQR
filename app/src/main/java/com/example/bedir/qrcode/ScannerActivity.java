package com.example.bedir.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.*;

public class ScannerActivity extends AppCompatActivity {
    //public Button scan_btn;
    DatabaseHelper myDb;
    FacultyInfo faculty;
    String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        myDb = new DatabaseHelper(this);
        faculty = new FacultyInfo();
        //scan_btn=findViewById(R.id.main_scan_btn);
        final Activity activity = this;
        //scan_btn.setOnClickListener(new View.OnClickListener() {
           /* @Override
            public void onClick(View view) {*/
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
        // }});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        scanResult = result.getContents();
        Intent i = new Intent(ScannerActivity.this, MainActivity.class);
        i.putExtra("codeid", scanResult);
        setResult(Activity.RESULT_OK, i);
        finish();


       // super.onActivityResult(requestCode, resultCode, data);


    }
}

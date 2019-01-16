package com.example.bedir.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QRShowActivity extends AppCompatActivity {
    TextView Name;
    TextView Info;
    TextView Since;
    ImageView ImageQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrshow);


        Name = (TextView) findViewById(R.id.qr_result_name_textview);
        Info = (TextView) findViewById(R.id.qr_result_info_textview);
        Since = (TextView) findViewById(R.id.qr_result_since_textview);
        ImageQR = (ImageView) findViewById(R.id.qr_result_image);

        Intent i=getIntent();
       FacultyInfo info = (FacultyInfo)i.getSerializableExtra("info");
        Name.setText(info.getName());
        Info.setText(info.getInfo());
        Since.setText(info.getSince());

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(info.getId(), BarcodeFormat.QR_CODE,500,500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ImageQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }



}

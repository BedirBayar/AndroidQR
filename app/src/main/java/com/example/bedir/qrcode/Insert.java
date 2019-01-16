package com.example.bedir.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class Insert extends AppCompatActivity {
    EditText txt_name, txt_info,txt_since;
    Button btn_generate;
    FacultyInfo facultyInfo;

DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        txt_name=(EditText)findViewById(R.id.faculty_name_edit_text);
        txt_info=(EditText)findViewById(R.id.faculty_info_edit_text);
        txt_since=(EditText)findViewById(R.id.since_edit_text);
        btn_generate=(Button) findViewById(R.id.btn_generate);
        databaseHelper=new DatabaseHelper(getApplicationContext()) ;
        facultyInfo=new FacultyInfo();


        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uniqueID = UUID.randomUUID().toString();
                facultyInfo.setId(uniqueID);
                facultyInfo.setName(txt_name.getText().toString());
                facultyInfo.setInfo(txt_info.getText().toString());
                facultyInfo.setSince(txt_since.getText().toString());
                databaseHelper.insertFaculty(facultyInfo);

            /*FacultyInfo info= new FacultyInfo();
                info=databaseHelper.getOneFaculty();
                txt_info.setText(info.getId());*/

                Intent QrShowScreen = new Intent(Insert.this, QRShowActivity.class);
              QrShowScreen.putExtra("info", facultyInfo);
               startActivity(QrShowScreen);

              //  final int result =1;
               // startActivityForResult(QrShowScreen,result);

            }
        });


    }
}

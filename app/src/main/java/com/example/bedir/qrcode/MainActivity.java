package com.example.bedir.qrcode;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String  codeid;
    DatabaseHelper db;
    FacultyInfo faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHelper(getApplicationContext());
        faculty=new FacultyInfo();
    }

    public void onClickScanButton(View view) {
        Intent scanScreen = new Intent(this, ScannerActivity.class);
        final int result =1;
        startActivityForResult(scanScreen,result);
    }

    public void onClickGenerateManualButton(View view) {
        Intent generateScreen = new Intent(this, Insert.class);
        final int result =1;
        startActivityForResult(generateScreen,result);
    }
    public void onClickGenerateUrlButton(View view) {
        Intent generateScreen = new Intent(this, UrlActivity.class);
        final int result =1;
        startActivityForResult(generateScreen,result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                codeid = data.getStringExtra("codeid");
                faculty=db.getOneFaculty(codeid);
                if(faculty.getId()!=null){
                    Intent QRShowScreen = new Intent(this, QRShowActivity.class);
                    QRShowScreen.putExtra("info",faculty);
                    startActivity(QRShowScreen);
                }
                else if(db.getUrl(codeid)){
                    if(!(codeid.startsWith("http") || codeid.startsWith("https"))){
                        codeid="https://"+codeid;
                    }
                    Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(codeid));
                    startActivity(viewIntent);
                }
                else{
                    Toast.makeText(this,"QR Code Not Found", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

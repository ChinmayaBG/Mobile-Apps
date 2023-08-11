package com.example.callsave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE};
        for(String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, permission + " " + "already granted", Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
            }
        }
        setContentView(R.layout.activity_main);

        int ids[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonash, R.id.buttonstar};
        EditText result = findViewById(R.id.editText);

        for(int id : ids){
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                result.setText(result.getText() + btn.getText().toString());
            });
        }

        Button callbutton = findViewById(R.id.buttoncall);
        callbutton.setOnClickListener(v->{
            if(result.getText().length() > 0) {
                String phStr = "tel:" + result.getText().toString();
                makePhoneCall(phStr);
            } else {
                Toast.makeText(MainActivity.this, "Unable to call",Toast.LENGTH_SHORT).show();
            }
        });

        Button clearbutton = findViewById(R.id.buttondel);
        clearbutton.setOnClickListener(v -> {
            if(result.getText().length() > 0) {
                CharSequence currentText = result.getText();
                result.setText(currentText.subSequence(0, currentText.length()-1));
            }
            else {
                result.setText("");
            }
        });

        Button savebutton = findViewById(R.id.buttonsave);
        savebutton.setOnClickListener(v -> {
            if(!result.getText().toString().isEmpty()){
                String phoneNumber = result.getText().toString();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber);
                startActivity(intent);
            }
        });
    }
    private void makePhoneCall(String PhStr){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(PhStr));
        startActivity(callIntent);
    }
}
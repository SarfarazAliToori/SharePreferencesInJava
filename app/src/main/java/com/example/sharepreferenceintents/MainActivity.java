package com.example.sharepreferenceintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.CleartextNetworkViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnExplicit, btnImplicit, btnOpenPhoneDialer, btnSaveDataInSharePref,
            btnGetDataFromSharePref, btnClearSharePref;
    EditText myEditText;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInit();
        myButtonListener();
    }

    public void myInit() {
        btnExplicit = findViewById(R.id.btn_Explict);
        btnImplicit = findViewById(R.id.btn_Implicit);
        btnOpenPhoneDialer = findViewById(R.id.btn_openDialer);
        btnSaveDataInSharePref = findViewById(R.id.btn_storeDataSharePref);
        btnGetDataFromSharePref = findViewById(R.id.btn_getDataSharePref);
        myEditText = findViewById(R.id.ed_myEditText);
        myTextView = findViewById(R.id.textView);
        btnClearSharePref = findViewById(R.id.btn_clear);
    }

    public void myButtonListener() {
        btnExplicit.setOnClickListener(this);
        btnImplicit.setOnClickListener(this);
        btnOpenPhoneDialer.setOnClickListener(this);
        btnSaveDataInSharePref.setOnClickListener(this);
        btnGetDataFromSharePref.setOnClickListener(this);
        myTextView.setOnClickListener(this);
        btnClearSharePref.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Explict: explicitIntent(); break;
            case R.id.btn_Implicit: implicitIntent(); break;
            case R.id.btn_openDialer: myOpenDialer(); break;
            case R.id.btn_storeDataSharePref: saveDataInSharePreferences(); break;
            case R.id.btn_getDataSharePref: readDataFromSharePreferences(); break;
            case R.id.btn_clear: clearSharePreferences(); break;
            default:
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }
    }

    public void explicitIntent() {
        String myStr = myEditText.getText().toString().trim();
        if (myStr.equals("")) {
            Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT).show();
        } else {
            Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
            myIntent.putExtra("myData", myStr);
            startActivity(myIntent);
        }
    }

    public void implicitIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text/Plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello World");
        startActivity(Intent.createChooser(intent, "Share"));
    }

    public void myOpenDialer() {
        String number =  myEditText.getText().toString().trim();
        if (number.equals("")) {
            myEditText.setError("Enter Number");
        } else {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }

    public void saveDataInSharePreferences() {

        String str = myEditText.getText().toString().trim();

        SharedPreferences myPre = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor sharePreEditor = myPre.edit();
        sharePreEditor.putString("stringKey", str);
//        sharePreEditor.putString("stringKey", "myString");
//        sharePreEditor.putInt("intKey", 1234);
//        sharePreEditor.putFloat("floatKey", 88);
//        sharePreEditor.putLong("longKey", 1000329);
//        sharePreEditor.putBoolean("booleanKey", true);
        sharePreEditor.apply();
        Toast.makeText(this, "Save Data", Toast.LENGTH_SHORT).show();
        // If I want to remove data from share preferences.
//        sharePreEditor.remove("longKey");
//        sharePreEditor.apply();
    }

    public void readDataFromSharePreferences() {
        SharedPreferences myPre = getPreferences(MODE_PRIVATE);
        String str =  myPre.getString("stringKey", null);
//        myPre.getInt("intKey", 0);
//        myPre.getFloat("floatKey",0);
//        myPre.getLong("longKey", 0);
//        myPre.getBoolean("booleanKey",false);

        myTextView.setText(str);
    }

    public void clearSharePreferences() {
        myTextView.setText("");
        SharedPreferences myPre = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = myPre.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Data is Clear", Toast.LENGTH_SHORT).show();

    }

}
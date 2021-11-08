package com.example.sharepreferenceintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportActionModeWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity  {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getData();
    }

    public void getData() {

        textView = findViewById(R.id.my_text_view);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("myData");
        textView.setText(data);
    }

}
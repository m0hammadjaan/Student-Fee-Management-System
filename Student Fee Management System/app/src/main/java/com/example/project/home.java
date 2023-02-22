package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class home extends AppCompatActivity implements View.OnClickListener {

    public CardView Admin , Student;
    TextView txt1,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txt1=findViewById(R.id.textView4);
        txt2=findViewById(R.id.textView5);

        Admin = (CardView) findViewById(R.id.c1);
        Student = (CardView) findViewById(R.id.c2);
        Admin.setOnClickListener(this);
        Student.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String txt=txt1.getText().toString();

        Intent i;
        switch (v.getId()) {
            case R.id.c1 :
                i = new Intent(this,Login.class);
                i.putExtra("admin", txt);
                startActivity(i);
                break;

            case R.id.c2 :
                i = new Intent(this,Login.class);
                startActivity(i);
                break;
        }
    }
}
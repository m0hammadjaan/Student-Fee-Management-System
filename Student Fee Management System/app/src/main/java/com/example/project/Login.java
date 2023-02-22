package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView txt1;
    EditText usr,pas;
    Button btnlog;
    SharedPreferences sharedPreferences;

    DBHelper db = new DBHelper(this,null,null,0);
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt1=findViewById(R.id.textView6);

        usr=findViewById(R.id.usr);
        pas=findViewById(R.id.pass);
        btnlog=findViewById(R.id.login);
        btnlog.setOnClickListener(this);

        Intent i = getIntent();
        txt1.setText(i.getStringExtra("admin"));

        if(i.getStringExtra("admin") != null)
        {

        }
        else
        {
            sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("sname") && sharedPreferences.contains("sid"))
            {
                Intent intent = new Intent(getApplicationContext(),Fee_details.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {

        String username=usr.getText().toString();
        String password =pas.getText().toString();
        Integer stu_id = id;

            if (usr.toString().isEmpty()) {
                usr.setError("Username must be provided");
            }
            if (pas.toString().isEmpty()) {
                pas.setError("Password must be provided");
            }
       else if (username.equals("Admin") && password.equals("1234"))
        {
            Toast.makeText(getApplicationContext(), "Admin Login successful", Toast.LENGTH_LONG).show();
            Intent login=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(login);
        }
       else
        {
            Cursor cursor=db.checklog(username,password);
            if(cursor.getCount()>0)
            {
                cursor.moveToFirst();
                int id = cursor.getInt(0);
                Toast.makeText(getApplicationContext(), "Student Login successful", Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sname",username);
                editor.putInt("sid",id);
                editor.commit();

                Intent login=new Intent(getApplicationContext(),Fee_details.class);
                startActivity(login);
            }
            else
                Toast.makeText(Login.this, "Invalid Username and Password", Toast.LENGTH_LONG).show();
        }
    }


}
package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class One extends Fragment implements View.OnClickListener{

    EditText sname,saddress,scontact,semail,spass;
    Button btn,btn2;


    public One() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_one, container, false);
        sname = view.findViewById(R.id.Name);
        saddress= view.findViewById(R.id.Address);
        scontact = view.findViewById(R.id.Contact);
        semail = view.findViewById(R.id.Email);
        spass = view.findViewById(R.id.Pass);
        btn=view.findViewById(R.id.button2);
        btn2=view.findViewById(R.id.button3);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

       int bid= v.getId();
       switch (bid)
       {
           case R.id.button2:
               Student s=new Student(sname.getText().toString(), saddress.getText().toString(), scontact.getText().toString(), semail.getText().toString(), spass.getText().toString());
               if (s.getName().isEmpty()) {
                   sname.setError("name must be provided");
               }

               if (s.getAddress().isEmpty()) {
                   saddress.setError("name must be provided");
               }
               if (s.getContact().isEmpty()) {
                   scontact.setError("name must be provided");
               }
               if (s.getEmail().isEmpty()) {
                   semail.setError("name must be provided");
               }
               if (s.getEmail().isEmpty()) {
                   semail.setError("invalid email");
               }
               if (s.getPassword().isEmpty()) {
                   spass.setError("name must be provided");
               }

               if (!s.getName().isEmpty() && !s.getAddress().isEmpty() && !s.getContact().isEmpty() && !s.getEmail().isEmpty() && !s.getPassword().isEmpty()) {
                   try {
                       DBHelper helper = new DBHelper(getActivity(), null, null, 0);
                       long res = helper.addStudent(s);

                       if (res > 0) {
                           Toast.makeText(getActivity(), "Student Added", Toast.LENGTH_LONG).show();
                           sname.setText("");
                           saddress.setText("");
                           scontact.setText("");
                           semail.setText("");
                           spass.setText("");
                           helper.close();
                       } else {
                           Toast.makeText(getActivity(), "User Not Added", Toast.LENGTH_LONG).show();
                       }
                   } catch (Exception ex) {
                       Log.i("msg", ex.getMessage().toString());
                   }
               }
               break;
           case R.id.button3:
               Intent intent = new Intent(getView().getContext(), home.class);
               startActivity(intent);
               break;
       }



    }

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/


}
package com.example.project;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Two extends Fragment implements View.OnClickListener {

    EditText txt1,txt2,txt3,txt4,txt5;
    Button btnupdate,btndel,btnsearch;

    public Two() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_two, container, false);

        txt1 = view.findViewById(R.id.txtsearch);
        txt2 = view.findViewById(R.id.txtname);
        txt3 = view.findViewById(R.id.txtadd);
        txt4= view.findViewById(R.id.txtcontact);
        txt5= view.findViewById(R.id.txtemail);



        btnsearch = view.findViewById(R.id.src);
        btnupdate = view.findViewById(R.id.upd);
        btndel = view.findViewById(R.id.del);

        btnsearch.setOnClickListener(this);
        btnupdate.setOnClickListener(this);
        btndel.setOnClickListener(this);

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {

        int vid = v.getId();

        int uid =0;
        DBHelper db = new DBHelper(getActivity(),null,null,0);

        if(txt1.getText().toString().isEmpty())
        {
            Toast.makeText(getActivity(),"Must provide User Id",Toast.LENGTH_LONG).show();
        }
        else
        {
            uid = Integer.parseInt(txt1.getText().toString());
        }

        switch(vid)
        {
            case R.id.src:
                Cursor row = db.getUser(uid);
                if(row.getCount() > 0)
                {
                    row.moveToFirst();
                    txt2.setText(row.getString(0));
                    txt3.setText(row.getString(1));
                    txt4.setText(row.getString(2));
                    txt5.setText(row.getString(3));
                }
                else
                {
                    Toast.makeText(getActivity(),"User not Found",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;

            case R.id.upd:

                Student s = new Student(txt2.getText().toString(),txt3.getText().toString(),txt4.getText().toString(),txt5.getText().toString());
                int res = db.updateUser(uid,s);
                if(res > 0)
                {
                    Toast.makeText(getActivity(),"User updated successfully",Toast.LENGTH_LONG).show();
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(),"User not updated",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;

            case R.id.del:
                int res2 = db.deleteUser(uid);
                if(res2 > 0)
                {
                    Toast.makeText(getActivity(),"User deleted successfully",Toast.LENGTH_LONG).show();
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                }
                else
                {
                    Toast.makeText(getActivity(),"User not deleted",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
        }
    }


    }

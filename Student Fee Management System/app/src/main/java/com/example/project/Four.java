package com.example.project;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.time.LocalDate;

public class Four extends Fragment implements View.OnClickListener {

    EditText src,nm , en, am, dt;
    RadioButton rb1,rb2,rb3;
    Button btnsearch, btnadd;
    String suggestion = "";

    public Four() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four, container, false);
        src = view.findViewById(R.id.txtsearch);
        nm = view.findViewById(R.id.stname);
        en = view.findViewById(R.id.enroll);
        am = view.findViewById(R.id.amount);
        dt = view.findViewById(R.id.dt);
        rb1=view.findViewById(R.id.radioButton);
        rb2=view.findViewById(R.id.radioButton2);
        rb3=view.findViewById(R.id.radioButton3);

        btnadd = view.findViewById(R.id.AddFee);
        btnsearch = view.findViewById(R.id.search);
        btnadd.setOnClickListener(this);
        btnsearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();

        int uid = 0;
        DBHelper db = new DBHelper(getActivity(), null, null, 0);

        if (src.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Must provide User Id", Toast.LENGTH_LONG).show();
        }
        else {
            uid = Integer.parseInt(src.getText().toString());

            switch (vid) {
                case R.id.search:
                    Cursor row = db.getUser(uid);
                    if (row.getCount() > 0) {
                        row.moveToFirst();
                        nm.setText(row.getString(0));
                        en.setText(uid+"");
                        LocalDate date = LocalDate.now();
                        String feedate = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
                        dt.setText(feedate);

                    } else {
                        Toast.makeText(getActivity(), "User not Found", Toast.LENGTH_LONG).show();
                    }
                    db.close();
                    break;

                case R.id.AddFee:

                    String Feetype = "";
                    if (rb1.isChecked()) {
                        Feetype = rb1.getText().toString();
                    }
                    if (rb2.isChecked()) {
                        Feetype = rb2.getText().toString();
                    }
                    if (rb3.isChecked()) {
                        Feetype = rb3.getText().toString();
                    }


                    if (am.toString().isEmpty()) {
                        am.setError("Amount must be provided");
                    }

                    if (dt.toString().isEmpty()) {
                        dt.setError("Invalid Date");
                    }

                    if (!am.toString().isEmpty() && !dt.toString().isEmpty()) {
                        try {
                            Fee f = new Fee(Integer.parseInt(en.getText().toString()), am.getText().toString(), Feetype, dt.getText().toString());
                            DBHelper helper = new DBHelper(getActivity(), null, null, 0);
                            long res = helper.addFee(f);

                            if (res > 0) {
                                Toast.makeText(getActivity(), "Fee Added", Toast.LENGTH_LONG).show();
                                nm.setText("");
                                en.setText("");
                                am.setText("");
                                dt.setText("");
                                helper.close();
                            } else {
                                Toast.makeText(getActivity(), "Fee Not Added", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Log.i("msg", ex.getMessage().toString());
                        }
                    }
            }
        }
        }
    }

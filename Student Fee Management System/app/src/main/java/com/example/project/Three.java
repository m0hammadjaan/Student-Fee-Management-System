package com.example.project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorLong;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Three extends Fragment {

    TableView<String[]> table;

    String[] tableHeader;
    String[][] tableData;

    public Three() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_three, container, false);

        tableHeader = new String[]{"ID", "Name","Address","Contact","Email","Password"};
        table = view.findViewById(R.id.table);
        table.setColumnCount(6);

        table.setHeaderBackgroundColor(Color.parseColor("#000000"));

        SimpleTableHeaderAdapter tableHeaderSettings = new SimpleTableHeaderAdapter(getActivity(),tableHeader);
        table.setHeaderAdapter(tableHeaderSettings);

        tableHeaderSettings.setTextColor(Color.parseColor("#FFEB3B"));
        tableHeaderSettings.setTextSize(15);

        //   table.setWeightSum(400);
        table.setColumnWeight(0,10);
        table.setColumnWeight(1,40);
        table.setColumnWeight(2,40);
        table.setColumnWeight(3,40);
        table.setColumnWeight(4,50);
        table.setColumnWeight(5,20);


        show();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public void show()
    {
        DBHelper helper = new DBHelper(getActivity(),null,null,0);
        ArrayList<Student> list = helper.getStudent();

        tableData = new String[list.size()][6];

        for (int i=0; i<list.size();i++)
        {
            tableData[i][0] = String.valueOf(list.get(i).getid());
            tableData[i][1] = list.get(i).getName();
            tableData[i][2] = list.get(i).getAddress();
            tableData[i][3] = list.get(i).getContact();
            tableData[i][4] = list.get(i).getEmail();
            tableData[i][5] = list.get(i).getPassword();
        }

        SimpleTableDataAdapter simpleTableDataAdapter = new SimpleTableDataAdapter(getActivity(),tableData);
        simpleTableDataAdapter.setTextColor(Color.parseColor("#000000"));
        table.setDataAdapter(simpleTableDataAdapter);

    }
}
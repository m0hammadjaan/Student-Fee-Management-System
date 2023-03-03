package com.example.project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class five extends Fragment {

    TableView<String[]> table;

    String[] tableHeader;
    String[][] tableData;

    public five() {
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

        View view=inflater.inflate(R.layout.fragment_five, container, false);

        tableHeader = new String[]{"ID", "Enrollment","Name","Amount","Type","Date"};
        table = view.findViewById(R.id.table);
        table.setColumnCount(6);

        table.setHeaderBackgroundColor(Color.parseColor("#000000"));

        SimpleTableHeaderAdapter tableHeaderSettings = new SimpleTableHeaderAdapter(getActivity(),tableHeader);
        table.setHeaderAdapter(tableHeaderSettings);

        tableHeaderSettings.setTextColor(Color.parseColor("#FFEB3B"));
        tableHeaderSettings.setTextSize(15);

        //   table.setWeightSum(400);
        table.setColumnWeight(0,10);
        table.setColumnWeight(1,25);
        table.setColumnWeight(2,40);
        table.setColumnWeight(3,20);
        table.setColumnWeight(4,20);
        table.setColumnWeight(5,30);


        show();

        return view;
    }
    public void show()
    {
        DBHelper helper = new DBHelper(getActivity(),null,null,0);
        ArrayList<StudentFee> list = helper.getStuFee();

        tableData = new String[list.size()][6];

        for (int i=0; i<list.size();i++)
        {
            tableData[i][0] = String.valueOf(list.get(i).getId());
            tableData[i][1] = String.valueOf(list.get(i).getEnrollment());
            tableData[i][2] = list.get(i).getName();
            tableData[i][3] = list.get(i).getAmount();
            tableData[i][4] = list.get(i).getType();
            tableData[i][5] = list.get(i).getDate();
        }

        SimpleTableDataAdapter simpleTableDataAdapter = new SimpleTableDataAdapter(getActivity(),tableData);
        simpleTableDataAdapter.setTextColor(Color.parseColor("#000000"));
        table.setDataAdapter(simpleTableDataAdapter);

    }
}
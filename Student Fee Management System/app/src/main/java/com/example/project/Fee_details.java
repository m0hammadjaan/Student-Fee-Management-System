package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Fee_details extends AppCompatActivity{

    TableView<String[]> table;
    Button btn;
    String[] tableHeader;
    String[][] tableData;
    TextView txt;

    NotificationManager notificationManager;
    NotificationChannel notificationChannel;
    Notification notification;
    int sid;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);

        txt=findViewById(R.id.textView15);

        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
         sid = sharedPreferences.getInt("sid",0);
        String val2 = sharedPreferences.getString("sname",null);

        if (sharedPreferences.contains("sname"))
        {
            txt.setText(val2);
        }

        LocalDate localDate = LocalDate.now();
        int date = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        DBHelper db = new DBHelper(Fee_details.this, null, null, 0);
        ArrayList<Fee> feeDetails = db.checkFee(sid);

       if(feeDetails.isEmpty())
       {
           Toast.makeText(Fee_details.this, "No fee has been paid, kindly pay your dues", Toast.LENGTH_LONG).show();
       }
       else
       {
           String feedate= feeDetails.get(0).getDate();
           String[] dateParts = feedate.split("/");

           int fmonth = Integer.parseInt(dateParts[1]);
           int fyear = Integer.parseInt(dateParts[2]);

           if(date <= 25)
           {
               if(fmonth == month && fyear==year)
               {
                   Toast.makeText(Fee_details.this, "Your Fee has been Paid", Toast.LENGTH_LONG).show();
               }
               else
               {
                   getFeeNotification();
               }
           }
       }

        //getFeeNotification();

        tableHeader = new String[]{"ID", "Enrollment","Amount","Fee_type","Date"};
        table = findViewById(R.id.table);
        table.setColumnCount(5);

        table.setHeaderBackgroundColor(Color.parseColor("#000000"));

        SimpleTableHeaderAdapter tableHeaderSettings = new SimpleTableHeaderAdapter(this,tableHeader);
        table.setHeaderAdapter(tableHeaderSettings);

        tableHeaderSettings.setTextColor(Color.parseColor("#FFEB3B"));
        tableHeaderSettings.setTextSize(15);

        // table.setWeightSum(100);
        table.setColumnWeight(0,10);
        table.setColumnWeight(1,15);
        table.setColumnWeight(2,15);
        table.setColumnWeight(3,15);
        table.setColumnWeight(4,15);
        show();
    }

    public void getFeeNotification()
    {

        try {

            int NOTIFICATION_ID = 234;
             notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String CHANNEL_ID = "my_channel_101";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "my_channel";
                String description = "This is my notif channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                 notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

                /*optional working */
                notificationChannel.setDescription(description);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.YELLOW);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 400, 300, 200, 100});
                notificationChannel.setShowBadge(false);
                /*optional working */
                notificationManager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(Fee_details.this, CHANNEL_ID);
            builder.setSmallIcon(R.drawable.msg);
            builder.setContentTitle("Fee Status");
            builder.setContentText("Dear Student, Pay your fee before submission deadline.");

            Intent intent= new Intent(Fee_details.this,FeeStatus.class);

            TaskStackBuilder stackBuilder= TaskStackBuilder.create(Fee_details.this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(intent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);

             notification = builder.build();

            notificationManager.notify(NOTIFICATION_ID, notification);
           // Toast.makeText(Fee_details.this, "Notification generated", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Log.d("Error", "Error: "+ex.getMessage());
        }

    }

    public void show()
    {
        DBHelper helper = new DBHelper(this,null,null,0);
        ArrayList<Fee> list = helper.getStudentFee(sid);

        tableData = new String[list.size()][5];

        for (int i=0; i<list.size();i++)
        {
            tableData[i][0] = String.valueOf(list.get(i).getId());
            tableData[i][1] = String.valueOf(list.get(i).getEnrollment());
            tableData[i][2] = list.get(i).getAmount();
            tableData[i][3] = list.get(i).getType();
            tableData[i][4] = list.get(i).getDate();
        }

        SimpleTableDataAdapter simpleTableDataAdapter = new SimpleTableDataAdapter(this,tableData);
        table.setDataAdapter(simpleTableDataAdapter);

    }

    public void logout(View view)
    {
        if(sharedPreferences.contains("sname") && sharedPreferences.contains("sid"))
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Fee_details.this, home.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
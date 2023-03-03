package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "androidDb";
    private static final String TABLE_NAME = "Student";
    private static final String TABLE_NAME2 = "Fee";
    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "s_id";
    private static final String COLUMN_NAME = "s_name";
    private  static final String COLUMN_ADDRESS="s_address";
    private  static final String COLUMN_CONTACT="s_contact";
    private static final String COLUMN_EMAIL = "s_email";
    private static final String COLUMN_PASSWORD = "s_password";

    //FEE TABLE

    private static final String FEE_ID = "id";
    private static final String FEE_ENROLLMENT= "enrollment";
    private  static final String FEE_AMOUNT="amount";
    private static final String FEE_TYPE = "type";
    private static final String FEE_DATE = "date";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "Create table " + TABLE_NAME + " (" + COLUMN_ID + " Integer Primary Key AUTOINCREMENT, " + COLUMN_NAME + " Text," + COLUMN_ADDRESS + " Text," + COLUMN_CONTACT + " Text, " + COLUMN_EMAIL + " Text," + COLUMN_PASSWORD + " Text)";
        String query2 = "Create table " + TABLE_NAME2 + " (" + FEE_ID + " Integer Primary Key AUTOINCREMENT," + FEE_ENROLLMENT + " Integer," + FEE_AMOUNT + " Text, " + FEE_TYPE + " Text," + FEE_DATE + " Text  )";

        try {
            db.execSQL(query);
            db.execSQL(query2);
            }
        catch (SQLException exception){
            Log.i("Error", exception.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      /*  db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);*/
    }
    public long addStudent(Student student)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME, student.getName());
        contentValues.put(COLUMN_ADDRESS, student.getAddress());
        contentValues.put(COLUMN_CONTACT, student.getContact());
        contentValues.put(COLUMN_EMAIL, student.getEmail());
        contentValues.put(COLUMN_PASSWORD, student.getPassword());

        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();

        try
        {
            long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
            return  result;
        }
        catch (Exception ex)
        {
            Log.d("Error", "addStudent: "+ex.getMessage());
        }
        return 0;
    }
    public ArrayList<Student> getStudent()
    {
        //use try catch here with SQLException
        ArrayList<Student> allData = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_ADDRESS,COLUMN_CONTACT,COLUMN_EMAIL,COLUMN_PASSWORD},null,null,null,null,null);

        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                Student student =  new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setAddress(cursor.getString(2));
                student.setContact(cursor.getString(3));
                student.setEmail(cursor.getString(4));
                student.setPassword(cursor.getString(5));
                allData.add(student);
                //allData.add(new User(cursor.getInt(0),cursor.getString(1),
                // cursor.getString(2),cursor.getString(3)));
            }
        }
        sqLiteDatabase.close();
        return allData;

    }
    public ArrayList<StudentFee> getStuFee()
    {
        //use try catch here with SQLException
        ArrayList<StudentFee> allData = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();

        String query = "select "+FEE_ID+","+FEE_ENROLLMENT+","+COLUMN_NAME+","+FEE_AMOUNT+","+FEE_TYPE+","+FEE_DATE+" from "+TABLE_NAME+","+TABLE_NAME2+" where "+COLUMN_ID+"="+FEE_ENROLLMENT;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                StudentFee fee =  new StudentFee();
                fee.setId(cursor.getInt(0));
                fee.setEnrollment(Integer.valueOf(cursor.getString(1)));
                fee.setName(cursor.getString(2));
                fee.setAmount(cursor.getString(3));
                fee.setType(cursor.getString(4));
                fee.setDate(cursor.getString(5));
                allData.add(fee);
                //allData.add(new User(cursor.getInt(0),cursor.getString(1),
                // cursor.getString(2),cursor.getString(3)));
            }
        }
        sqLiteDatabase.close();
        return allData;

    }


    public ArrayList<Fee> getStudentFee(int id)
    {
        //use try catch here with SQLException
        ArrayList<Fee> allData = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME2,new String[]{FEE_ID,FEE_ENROLLMENT,FEE_AMOUNT,FEE_TYPE,FEE_DATE},FEE_ENROLLMENT+"="+id,null,null,null,null);

        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                Fee fee =  new Fee();
                fee.setId(cursor.getInt(0));
                fee.setEnrollment(Integer.valueOf(cursor.getString(1)));
                fee.setAmount(cursor.getString(2));
                fee.setType(cursor.getString(3));
                fee.setDate(cursor.getString(4));
                allData.add(fee);
                //allData.add(new User(cursor.getInt(0),cursor.getString(1),
                // cursor.getString(2),cursor.getString(3)));
            }
        }
        sqLiteDatabase.close();
        return allData;

    }


    public long addFee(Fee fee)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(FEE_ENROLLMENT, fee.getEnrollment());
        contentValues.put(FEE_AMOUNT, fee.getAmount());
        contentValues.put(FEE_TYPE, fee.getType());
        contentValues.put(FEE_DATE, fee.getDate());

        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();

        try
        {
            long result = sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);
            sqLiteDatabase.close();
            return  result;
        }
        catch (Exception ex)
        {
            Log.d("Error", "addFee: "+ex.getMessage());
        }
        return 0;
    }

    public int updateUser(int id, Student student) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, student.getName());
        contentValues.put(COLUMN_ADDRESS, student.getAddress());
        contentValues.put(COLUMN_CONTACT, student.getContact());
        contentValues.put(COLUMN_EMAIL, student.getEmail());
        //contentValues.put(COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int res = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=" + id, null);
        sqLiteDatabase.close();
        return res;
    }
    public int deleteUser(int id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        int res= sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"="+id,null);
        sqLiteDatabase.close();

        return res;
    }
    public Cursor getUser(int id)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{COLUMN_NAME,COLUMN_ADDRESS,COLUMN_CONTACT,COLUMN_EMAIL},COLUMN_ID+"="+id,null,null,null,null);
        return cursor;
    }


    public ArrayList<Fee> checkFee(int id)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME2,new String[]{FEE_ENROLLMENT,FEE_TYPE,FEE_AMOUNT,FEE_DATE},FEE_ENROLLMENT+"="+id,null,null,null,null);
        ArrayList<Fee> allData = new ArrayList<>();
        if(cursor.getCount() >= 0)
        {
            while (cursor.moveToNext())
            {
                Fee fee =  new Fee();
                fee.setEnrollment(Integer.valueOf(cursor.getString(0)));
                fee.setAmount(cursor.getString(2));
                fee.setType(cursor.getString(1));
                fee.setDate(cursor.getString(3));
                allData.add(fee);
            }
        }
        return allData;
    }


    public  Cursor checklog(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("Select * from Student where s_name = ? and s_password = ?", new String[]{username,password});
        return cursor;
    }
}

package com.example.admin.quanlysinhvien_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 6/30/2016.
 */
public class StudentModify {
    private DBHelper dbHelper;

    public StudentModify(Context context) {
        dbHelper= new DBHelper(context);
    }

    //PT Them
    public void insert(Student student){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_AGE,student.getAge());
        values.put(DBHelper.KEY_EMAIL, student.getEmail());
        values.put(DBHelper.KEY_NAME, student.getName());
        values.put(DBHelper.KEY_SDT,student.getSdt());

        db.insert(DBHelper.TABLE_NAME,null,values);
        db.close();
    }

    //PT Sua
    public void update(Student student){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.KEY_AGE,student.getAge());
        values.put(DBHelper.KEY_EMAIL, student.getEmail());
        values.put(DBHelper.KEY_NAME, student.getName());
        values.put(DBHelper.KEY_SDT,student.getSdt());

        db.update(DBHelper.TABLE_NAME,values, DBHelper.KEY_ID+ "=?",new String[]{String.valueOf(student.getStudent_id())});
        db.close();

    }

    //PT xoa
    public void delete(int student_id){
        //Mo ket noi den Database
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NAME,DBHelper.KEY_ID+"=?",new String[]{String.valueOf(student_id)});
        db.close();
    }


    //lay tat ca du lieu trong bang
    public Cursor getStudentList(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME,new String[]{DBHelper.KEY_NAME,DBHelper.KEY_EMAIL,DBHelper.KEY_AGE},null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Lay 1 du lieu
    public Student fetchStudentByID(int student_id){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query(DBHelper.TABLE_NAME,new String[]{DBHelper.KEY_ID,DBHelper.KEY_NAME,DBHelper.KEY_EMAIL,DBHelper.KEY_AGE,DBHelper.KEY_SDT},DBHelper.KEY_ID+"=?",new String[]{String.valueOf(student_id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
    }
}

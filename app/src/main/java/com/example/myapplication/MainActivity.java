package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DBHelper database;
    private Button insertButton, deleteButton, selectButton, updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new DBHelper(this,"database",null,1);
        SQLiteDatabase mydb = database.getReadableDatabase();
        insertButton=findViewById(R.id.button);
        deleteButton=findViewById(R.id.button2);
        selectButton=findViewById(R.id.button3);
        updateButton=findViewById(R.id.button4);

        mydb.execSQL("DELETE FROM student");

        insertButton.setOnClickListener(new View.OnClickListener() {//insert
            @Override
            public void onClick(View view) {
                ContentValues values1=new ContentValues();
                ContentValues values2=new ContentValues();
                ContentValues values3=new ContentValues();
                ContentValues values4=new ContentValues();
                values1.put("name","范文杰");
                values1.put("age",22);
                values2.put("name","王宇");
                values2.put("age",18);
                values3.put("name","何家健");
                values3.put("age",18);
                values4.put("name","胡阿鑫");
                values4.put("age",13);
                mydb.insert("student",null,values1);
                mydb.insert("student",null,values2);
                mydb.insert("student",null,values3);
                mydb.insert("student",null,values4);
                Log.d("db","insert "+values1+ " " + values2+ " " +values3);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {//delete
            @Override
            public void onClick(View view) {
                mydb.delete("student", "age>?", new String[]
                        {"17"});
                Log.d("db","delete age>17");
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {//select
            @SuppressLint("Recycle")
            @Override
            public void onClick(View view) {
                Cursor cursor1=mydb.query("student",
                        new String[]{"name,age,id"},"age>?",
                        new String[]{"0"},null,null,null);
                Log.d("db","start,age>0:表中有数据"+cursor1.getCount()+"条");
                while (cursor1.moveToNext()) {
                    @SuppressLint("Range") String name =
                            cursor1.getString(cursor1.
                                    getColumnIndex("name"));
                    @SuppressLint("Range") int age =
                            cursor1.getInt(cursor1.
                                    getColumnIndex("age"));
                    @SuppressLint("Range") int id =
                            cursor1.getInt(cursor1.
                                    getColumnIndex("id"));
                    Log.d("db","id="+id+"|name="+name+"|age="+age);
                }
                Cursor cursor2=mydb.rawQuery("select * from student where name=?",
                        new String[]{"b1s"});
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {//改
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                values.put("name","范文杰");
                values.put("age",233);
                mydb.update("student",values,"age>=?",new
                        String[]{"21"});
                Log.d("db","修改 age>21,"+values);
            }
        });
    }


}
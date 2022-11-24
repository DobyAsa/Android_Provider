package com.example.myprovider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class MyDAO {
    private SQLiteDatabase database;
    private SQLiteOpenHelper myopenhelper;
    private Context context;

    private Uri uri=Uri.parse("content://doby.provider");

    public MyDAO(Context context){
        this.context=context;
        myopenhelper=new MyDBHelper(context,"dobyDB",null,1);
        database=myopenhelper.getReadableDatabase();

        database.execSQL("drop table if exists person");
        database.execSQL("create table person(id integer primary key autoincrement,"+" name varchar, age integer)");
        Log.d("doby", "已成功创建数据库。");
    }

    public Uri insert(Uri uri, ContentValues values){
        long rowID=database.insert("person",null,values);

        if(rowID == -1){
            Log.d("doby","DAO: 数据插入失败");
            return  null;
        }
        else {
            Uri insertUri= ContentUris.withAppendedId(uri,rowID);
            Log.d("doby","ContentUris:"+insertUri.toString());
            context.getContentResolver().notifyChange(insertUri,null);
            return insertUri;
        }
    }
}

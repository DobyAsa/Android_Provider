package com.example.myresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_resolver = findViewById(R.id.btn_resolver);
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://doby.provider/person");

        ContentValues values = new ContentValues();
        values.put("name","何家健");
        values.put("age",20);

        btn_resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolver.insert(uri,values);
                Log.d("doby", "Resolver: 已成功插入数据。");
            }
        });
    }
}
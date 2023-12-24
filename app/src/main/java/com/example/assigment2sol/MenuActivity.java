package com.example.assigment2sol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {
    private ListView list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        list = findViewById(R.id.list);
        Intent intent = getIntent();
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(MenuActivity.this, TimeActivity.class);
                    startActivity(intent);
                }
                else
                    if(position==1){
                        Intent intent = new Intent(MenuActivity.this, SummerActivity.class);
                        startActivity(intent);
                }
            }
        };

        list.setOnItemClickListener(itemClickListener);
    }
}
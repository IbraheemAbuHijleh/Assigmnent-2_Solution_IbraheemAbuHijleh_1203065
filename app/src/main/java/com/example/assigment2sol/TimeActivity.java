package com.example.assigment2sol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TimeActivity extends AppCompatActivity {

    private Spinner spinner;
    private RequestQueue q;

    private TextView textView2;
    private TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_activy);
        spinner=findViewById(R.id.spinner);
        textView2 = findViewById(R.id.textView2);
        Intent intent=getIntent();
        q = Volley.newRequestQueue(this);
        textView3=findViewById(R.id.textView3);
    }

    public void btnLoginOnClick(View view){
        String city = spinner.getSelectedItem().toString();
        String []NewCity=city.split(" \\(");
        String newcity = NewCity[0];
        String baseURL = "https://worldtimeapi.org/api/timezone/Asia/";
        String url = baseURL + newcity;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String datatime = response.getString("datetime");
                    String[] dateTimeParts = datatime.split("T");
                    if (dateTimeParts.length == 2) {
                        String date = dateTimeParts[0];
                        String time = dateTimeParts[1];
                        String [] TIME=time.split(":");
                        String TimeNEW=TIME[0]+":"+TIME[1];
                        textView2.setText(TimeNEW);
                        textView3.setText(date);
                    } else {
                        textView2.setText("Invalid datetime format");
                    }

                } catch (Exception e) {
                    Log.d("Ibraheem", "Error: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Ibraheem", "Hi");
                        Log.d("volley_error", error.getMessage().toString());
                    }
                }

        );

        q.add(jsonObjectRequest);
    }

    }
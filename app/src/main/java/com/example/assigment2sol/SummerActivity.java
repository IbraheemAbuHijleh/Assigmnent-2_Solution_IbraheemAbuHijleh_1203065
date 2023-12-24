package com.example.assigment2sol;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SummerActivity extends AppCompatActivity {
    private RequestQueue q;
    private Spinner spinner2;
    private Button button3;
    private TextView textView4;
    private TextView textView5;

    private TextView textView6;

    private TextView textView7;
    private RadioGroup  groupradio;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summer_activy);
        spinner2 = findViewById(R.id.spinner2);
        button3 = findViewById(R.id.button3);
        textView4 = findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        q = Volley.newRequestQueue(this);
    }
    public void btnLoginOnClick(View view){
        String city = spinner2.getSelectedItem().toString();
        String[] NewCity = city.split(" \\(");
        String newcity = NewCity[0];
        String baseURL = "https://worldtimeapi.org/api/timezone/Asia/";
        String url = baseURL + newcity;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean datatime = response.getBoolean("dst");
                    if (datatime) {
                        textView4.setText("The Time is summer");

                    } else {
                        textView4.setText("The Time is not summer");
                    }
                    String Number_Of_Week=response.getString(String.valueOf("week_number"));
                    textView7.setText("The current Week Number is :" +" " +Number_Of_Week);
                    String Day=response.getString(String.valueOf("day_of_week"));
                    if(Day.equals("0")){
                        textView5.setText("The day is Sunnday");
                    }
                    if(Day.equals("1")){
                        textView5.setText("The day is :Monday");
                    }
                    if(Day.equals("2")){
                        textView5.setText("The Day is :Tuesday");
                    }
                    if(Day.equals("3")){
                        textView5.setText("The day is : Wednesday");
                    }
                    if(Day.equals("4")){
                        textView5.setText("The Day is : Thursday");
                    }
                    if(Day.equals("5")){
                        textView5.setText("The Day is Friday");
                    }
                    if(Day.equals("6")) {
                        textView5.setText("The Day is : Saturday");
                    }
                    String Day_of_Year=response.getString(String.valueOf("day_of_year"));
                    textView6.setText("The Day Number Of Year is :" +" " + Day_of_Year);
                } catch (JSONException e) {
                    Log.d("Ibraheem", "JSON Error: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = error.getMessage() != null ? error.getMessage() : "Unknown error";
                Log.d("volley_error", errorMessage);
            }
        }
        );
        q.add(jsonObjectRequest);
    }
    }


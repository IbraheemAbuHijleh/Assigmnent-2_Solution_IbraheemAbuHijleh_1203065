package com.example.assigment2sol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";

    private EditText edtName;
    private TextView error;
    private EditText edtPassword;
    private CheckBox chk;
    private boolean flag = false;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupSharedPrefs();
        checkPrefs();

    }

    private void checkData() {
        if(edtName.getText().equals("") || edtPassword.getText().equals("")){
          error.setText("Plese Enter All Data");
        }
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);
        if(flag){
            String name = prefs.getString(NAME, "");
            String password = prefs.getString(PASS, "");
            edtName.setText(name);
            edtPassword.setText(password);
            chk.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        edtName=findViewById(R.id.edtName);
        edtPassword=findViewById(R.id.edtPassword);
        chk=findViewById(R.id.chk);
        error=findViewById(R.id.error);
    }

    public void btnLoginOnClick(View view) {
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();
          if(name.isEmpty() || password.isEmpty()){
              error.setText("Plese Enter All Data");
          }
          else {
              if (chk.isChecked()) {
                  if (!flag) {
                      editor.putString(NAME, name);
                      editor.putString(PASS, password);
                      editor.putBoolean(FLAG, true);
                      editor.commit();

                  }
              }
              Intent intent = new Intent(this, MenuActivity.class);
              startActivity(intent);
          }

    }

    public void onStop() {
        super.onStop();
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();

        if(chk.isChecked()){
            if(!flag) {
                editor.putString(NAME, name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }

        }

    }
}
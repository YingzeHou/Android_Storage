package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username","").equals("")){
            String username = sharedPreferences.getString("username","");
            Intent notePage = new Intent(this,NoteActivity.class);
            notePage.putExtra("username", username);
            startActivity(notePage);
        }
        else{
            setContentView(R.layout.activity_main);
        }
    }

    public void onClick(View view){
        EditText username = (EditText)findViewById(R.id.username);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username.getText().toString()).apply();
        goToSecondScreen(username.getText().toString());
    }

    public void goToSecondScreen(String username){
        Intent intent = new Intent(this,NoteActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
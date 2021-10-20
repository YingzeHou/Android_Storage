package com.example.lab5_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    int noteid=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText editText = (EditText) findViewById(R.id.editnote);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer id = (Integer) bundle.get("noteId");
        noteid = getIntent().getExtras()==null?-1: (int) getIntent().getExtras().get("noteId");


        if(noteid!=-1){
            Note note = NoteActivity.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveNote(View view){
        EditText noteContent = (EditText) findViewById(R.id.editnote);
        String content = noteContent.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_m1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid==-1){
            title = "NOTE_"+(NoteActivity.notes.size()+1);
            dbHelper.saveNotes(username, title,content,date);
        }
        else{
            title = "NOTE_"+(noteid+1);
            dbHelper.updateNote(username,title,content,date);
        }

        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }
}
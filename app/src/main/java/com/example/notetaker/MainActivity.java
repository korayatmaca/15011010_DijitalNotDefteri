package com.example.notetaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity<notes> extends AppCompatActivity {

    private ListView listView_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_notes = (ListView)findViewById(R.id.listview_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_menu_new_note:
                // noteActivityi newnote  da aç
                Intent newNoteActivity = new Intent(this,NoteActivity.class);
                startActivity(newNoteActivity);
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView_notes.setAdapter(null);

        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);

        if(notes == null || notes.size() == 0  ){
            Toast.makeText(this,"there is no notes",Toast.LENGTH_SHORT).show();
            return;
             }else {
            NoteAdapter na = new NoteAdapter(this,R.layout.item_note, notes);
            listView_notes.setAdapter(na);

            listView_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Note)listView_notes.getItemAtPosition(position)).getaDateTime()
                            +Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent =  new Intent(getApplicationContext(),NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);
                }
            });
            }
        }
    }



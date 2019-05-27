package com.example.notetaker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;

    private String noteFileName;
    private Note LoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        etTitle=(EditText)findViewById(R.id.note_et_title);
        etContent=(EditText)findViewById(R.id.note_et_content);

        noteFileName = getIntent().getStringExtra("NOTE_FILE");

        if(noteFileName!= null && !noteFileName.isEmpty()){
            LoadedNote = Utilities.getNoteByName(this,noteFileName);

            if(noteFileName != null){
                etTitle.setText(LoadedNote.getTitle());
                etContent.setText(LoadedNote.getContent());

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_note_new,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save_note:
                saveNote();

            case R.id.action_delete_note:
                deleteNote();
        };
        return true;
    }


    private void saveNote(){

        Note note;

        if (etTitle.getText().toString().trim().isEmpty()||etContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"must be entered title or content",Toast.LENGTH_SHORT).show();
            return;
        }

        if (LoadedNote==null){
             note = new Note(System.currentTimeMillis(),etTitle.getText().toString(),
                    etContent.getText().toString());

        } else {
             note = new Note(LoadedNote.getaDateTime(),etTitle.getText().toString(),
                    etContent.getText().toString());

        }


        if(Utilities.saveNote(this,note)){
            Toast.makeText(this,"Note Saved!",
                    Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this,"Note can not Saved!",
                    Toast.LENGTH_SHORT).show();

        }
        finish();
    }

    private void deleteNote() {
        if(LoadedNote == null){
            finish();
        }else{

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Are you Sure?")
                    .setMessage("You are deleting "+etTitle.getText().toString())
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext(),
                                    LoadedNote.getaDateTime()
                                            +Utilities.FILE_EXTENSION);

                            Toast.makeText(getApplicationContext(),etTitle.getText().toString()+"is deleted",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no",null)
                    .setCancelable(false);

            dialog.show();

        }
    }
}

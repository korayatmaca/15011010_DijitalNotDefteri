package com.example.notetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter( Context context, int resource,  ArrayList<Note> notes) {
        super(context, resource, notes);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        //return super.getView(position, convertView, parent);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note,null);
        }

        Note note = getItem(position);

        if(note != null){
            TextView title = (TextView)convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView)convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView)convertView.findViewById(R.id.list_note_content);

            title.setText(note.getTitle());
            date.setText(note.getDateTimeString(getContext()));

            if (note.getContent().length() > 20){
                content.setText(note.getContent().substring(0,20));
                // not 20 karakterden fazlaysa ilk 20 karakteri göster
            }else {
                //değilse hepsini göster
                content.setText(note.getContent());

            }
        }
        return convertView;
    }
}

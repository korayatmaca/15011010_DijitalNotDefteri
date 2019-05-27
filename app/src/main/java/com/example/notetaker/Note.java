package com.example.notetaker;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Note implements Serializable {

    private long aDateTime;
    private String title;
    private String content;

    public Note(long aDateTime, String title, String content) {
        this.aDateTime = aDateTime;
        this.title = title;
        this.content = content;
    }

    public void setaDateTime(long aDateTime) {
        this.aDateTime = aDateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getaDateTime() {
        return aDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public String getDateTimeString(Context context){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",context.getResources().getConfiguration().locale);

         sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(new Date(aDateTime));

    }
}

package com.example.toddo_list.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDB extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    private static NoteDB noteDB;

    //Singleton class

    public static NoteDB getInstance(Context context){
        if(noteDB == null){
            noteDB = buildDatabase(context);

        }
        return noteDB;
    }

    private static NoteDB buildDatabase(Context context){
        return Room.databaseBuilder(context,
                NoteDB.class,
                "note").allowMainThreadQueries().build();
    }
}

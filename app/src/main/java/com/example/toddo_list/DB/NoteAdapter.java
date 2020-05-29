package com.example.toddo_list.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toddo_list.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    public List<Note> notes;
    Context context;

    public NoteAdapter(List<Note> notes, Context context){
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_note, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.note.setText(notes.get(position).getContent());
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView note;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            note = itemView.findViewById(R.id.note);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}

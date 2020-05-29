package com.example.toddo_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.toddo_list.DB.Note;
import com.example.toddo_list.DB.NoteAdapter;
import com.example.toddo_list.DB.NoteDB;
import com.example.toddo_list.DB.NoteDao;

public class MainActivity extends AppCompatActivity {
    EditText todo;
    Button add;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todo = findViewById(R.id.todo);
        recyclerView = findViewById(R.id.recycler_view);
        add = findViewById(R.id.add);

        final NoteDao dao = NoteDB.getInstance(this).getNoteDao();
        final NoteAdapter adapter = new NoteAdapter(dao.getAll(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                dao.deleteNote(dao.getAll().get(position));
                adapter.notes = dao.getAll();
                adapter.notifyDataSetChanged();
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoText = todo.getText().toString();

                if(!todoText.isEmpty()){
                    dao.insertNote(new Note(0, todoText));
                    todo.setText("");
                    adapter.notes = dao.getAll();
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}

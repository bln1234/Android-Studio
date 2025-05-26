package com.example.todo_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText todo;
    Button submitBtn;
    RecyclerView todoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        todoList = findViewById(R.id.TodoList);
        todo = findViewById(R.id.Todo);
        submitBtn = findViewById(R.id.submit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        todoList.setLayoutManager(layoutManager);
        List<String> list = new ArrayList<>();
        list.add(" 待办事项1");
        list.add(" 待办事项2");
        todoAdapter adapter = new todoAdapter(list);
        todoList.setAdapter(adapter);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = todo.getText().toString();
                if(newTodo !="") {
                    newTodo = " " + newTodo;
                    list.add(newTodo);
                    adapter.notifyItemInserted(list.size() - 1);
                    todo.setText("");
                }
            }
        });
    }
}
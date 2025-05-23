package com.example.todo_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.todoViewHolder> {
    private List<String> todoList;
    public todoAdapter(List<String> todoData) { //adapter类的构造函数，传入一个字符串列表
         todoList = todoData;
    }
    @Override
    public todoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_view, parent, false); //从xml中使用todo_view视图
        return new todoViewHolder(view); //用上面的todo_view视图来创建新的ViewHolder对象
    }
    @Override
    public void onBindViewHolder(todoViewHolder holder,int position ){ //单个RecyclerView项目绑定数据，将数据的内容显示在视图上
        String todo = todoList.get(position);
        holder.todo.setText(todo); //获取数据并显示
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // 从列表中移除项目
                    todoList.remove(currentPosition);

                    // 更改通知适配器以更新视图
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, todoList.size());
                }
            }
        });
    }
    @Override
    public int getItemCount(){ //列表中数据项总数
        return todoList.size();
    }
    public static class todoViewHolder extends RecyclerView.ViewHolder{ //用于表示每个项目视图
        public TextView todo;
        public Button deleteBtn;
        public todoViewHolder(View view){ //构造函数，初始化视图组件
            super(view);
            todo = view.findViewById(R.id.content);
            deleteBtn = view.findViewById(R.id.delete);
        }
    }
}

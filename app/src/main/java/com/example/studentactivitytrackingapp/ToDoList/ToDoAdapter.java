package com.example.studentactivitytrackingapp.ToDoList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentactivitytrackingapp.R;
import com.example.studentactivitytrackingapp.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder> {

    private List<ToDo> todos = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item,viewGroup,false);
        return new ToDoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder toDoHolder, int i) {

                ToDo currentToDO = todos.get(i);
                toDoHolder.textView_title.setText(currentToDO.getTitle());
                toDoHolder.text_check.setChecked(currentToDO.isCheckable_list());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ToDoHolder extends RecyclerView.ViewHolder {

         TextView textView_title;
        //private TextView textView_description;
        CheckBox text_check;

        public ToDoHolder(@NonNull View itemView) {
            super(itemView);

            textView_title = itemView.findViewById(R.id.text_view_title_todo);
            text_check = itemView.findViewById(R.id.text_view_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position!=RecyclerView.NO_POSITION)
                        listener.onItemClick(todos.get(position));

                }
            });
        }
    }

    public  interface OnItemClickListener {
        void onItemClick(ToDo todo);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public  void setDoTo (List<ToDo> todos){
        this.todos  = todos;
        notifyDataSetChanged();
    }

    public ToDo getToDoAt(int i){
        return  todos.get(i);
    }


}

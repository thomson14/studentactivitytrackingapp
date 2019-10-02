package com.example.studentactivitytrackingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {


    private List<Note>  notes = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item,viewGroup,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

        Note currentNote = notes.get(i);
        noteHolder.textView_title.setText(currentNote.getTitle());
        noteHolder.textView_description.setText(currentNote.getDescription());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textView_title;
        private TextView textView_description;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.text_view_title);
            textView_description = itemView.findViewById(R.id.text_view_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position!=RecyclerView.NO_POSITION)
                    listener.onItemClick(notes.get(position));

                }
            });

        }
    }

    public  interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
       this.listener = listener;
    }

    public  void setNotes (List<Note> notes){
        this.notes  = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int i){
        return  notes.get(i);
    }
}

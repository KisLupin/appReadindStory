package com.t3h.appstoryreading;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private IList inter;
    public ListAdapter (IList inter){
        this.inter = inter;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_story,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
        ListStory listStory = inter.getStoryName(i);
        listViewHolder.textView.setText(listStory.getNameStory());
        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.onClick(listViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return inter.getSize();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name_of_story);
        }
    }
}

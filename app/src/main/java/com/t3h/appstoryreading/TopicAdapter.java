package com.t3h.appstoryreading;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHoder> {

    private ITopic inter;
    public TopicAdapter(ITopic inter){
        this.inter =inter;
    }
    @NonNull
    @Override
    public TopicViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_topic, viewGroup, false);
        return new TopicViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicViewHoder topicViewHoder, int i) {
        Topic topic = inter.getTopic(i);
        topicViewHoder.tvName.setText(topic.getName());
        topicViewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.onClick(topicViewHoder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getSize();
    }

    static class TopicViewHoder extends RecyclerView.ViewHolder{
        private TextView tvName;
        public TopicViewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}

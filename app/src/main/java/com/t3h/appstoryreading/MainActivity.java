package com.t3h.appstoryreading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ITopic {
    private ManagerSql managerSql;
    private RecyclerView rc;
    private List<Topic> topics;
//    private List<Topic> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managerSql = new ManagerSql(this);
        topics = managerSql.getAllTopic();

        rc = findViewById(R.id.rc);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new TopicAdapter(this));
    }

    @Override
    public Topic getTopic(int position) {
        return topics.get(position);
    }

    @Override
    public int getSize() {
        return topics.size();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent();
        intent.setClass(this, StoryActivity.class);
        intent.putExtra("ID", topics.get(position).getId());
        intent.putExtra("NAME",topics.get(position).getName());
        startActivity(intent);
    }
}


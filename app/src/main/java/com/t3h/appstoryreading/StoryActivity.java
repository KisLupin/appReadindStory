package com.t3h.appstoryreading;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.appstoryreading.databinding.ActivityListstoryBinding;

import java.util.List;

public class StoryActivity extends AppCompatActivity implements IList{

    private List<ListStory> listStoryList;
    private RecyclerView rc;
    private ManagerSql managerSql;
    private ImageView back;
    private TextView nameStory;
    ActivityListstoryBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_liststory);

        managerSql = new ManagerSql(this);
        Intent intent = getIntent();
        listStoryList = managerSql.getAllNameStory(intent.getIntExtra("ID",0));

        rc = findViewById(R.id.rc_story);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new ListAdapter(this));

        nameStory = findViewById(R.id.type_to_read);
        nameStory.setText(intent.getStringExtra("NAME"));

        back =findViewById(R.id.before_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public ListStory getStoryName(int position) {
        return listStoryList.get(position);
    }

    @Override
    public int getSize() {
        return listStoryList.size();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent();
        intent.setClass(this,ContentOfStory.class);
        intent.putExtra("NAME",listStoryList.get(position).getNameStory());
        intent.putExtra("ID",listStoryList.get(position).getIdName());
        startActivity(intent);

    }
}

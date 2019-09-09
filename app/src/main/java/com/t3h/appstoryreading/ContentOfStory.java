package com.t3h.appstoryreading;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.t3h.appstoryreading.databinding.ActivityContentBinding;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ContentOfStory extends AppCompatActivity {

    private ImageView bt;
    private HtmlTextView contentStory;
    private TextView name;
    private ManagerSql managerSql;
    ActivityContentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_content);
        Intent intent = getIntent();

        contentStory = findViewById(R.id.content_story);
        managerSql = new ManagerSql(this);
        contentStory.setHtml(managerSql.getContentStory(intent.getIntExtra("ID",0)));
        name = findViewById(R.id.name_story);
        name.setText(intent.getStringExtra("NAME"));

        bt = findViewById(R.id.before_button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

package com.example.projectand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.projectand.Adapter.SubjectAdapter;
import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    private RecyclerView recSub;
    private SubjectAdapter adapter;
    private Database db;
    private ImageView btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        initView();
        db = new Database(this);
        recSub.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new SubjectAdapter(this);
        List<Subject> subjects = db.getAllSubject();
        adapter.setSubjects(subjects);
        recSub.setAdapter(adapter);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initView() {
        recSub = findViewById(R.id.recSub);
        btReturn = findViewById(R.id.btReturn);
    }
}
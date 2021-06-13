package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projectand.Adapter.QuesAdapter;
import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListQuesActivity extends AppCompatActivity {

    private RecyclerView recQues;
    private FloatingActionButton fabAddQues;
    private ImageView btReturn;
    private Database db;
    private QuesAdapter adapter;
    private int idSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ques);
        initView();
        db = new Database(this);
        recQues.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new QuesAdapter(this);
        Intent intent =getIntent();
        idSub = intent.getIntExtra("idSub",1);
        List<Question> questions = db.getBySub(idSub);
        adapter.setQues(questions);
        recQues.setAdapter(adapter);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabAddQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQuesActivity.this, AddQuesActivity.class);
                intent.putExtra("idSub",idSub);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        List<Question> questions = db.getBySub(idSub);
        adapter.setQues(questions);
        recQues.setAdapter(adapter);
        super.onRestart();
    }

    private void initView(){
        recQues = findViewById(R.id.recQues);
        fabAddQues = findViewById(R.id.fabAddQues);
        btReturn = findViewById(R.id.btReturn);
    }
}
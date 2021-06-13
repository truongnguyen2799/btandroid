package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.io.Serializable;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btAll, btSelect, btEdit;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        btAll.setOnClickListener(this);
        btSelect.setOnClickListener(this);
        btEdit.setOnClickListener(this);
        db = new Database(this);
    }

    private void initView() {
        btAll = findViewById(R.id.btAll);
        btSelect = findViewById(R.id.btSelect);
        btEdit = findViewById(R.id.btEdit);
    }

    @Override
    public void onClick(View v) {
        if (v == btAll) {
            List<Question> questions = db.getAllQues();
            if (questions.isEmpty()) {
                Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(HomeActivity.this, TimeActivity.class);
                int numQ = questions.size();
                int[] traloi = new int[numQ + 1];
                for (int i = 0; i <= (numQ); i++) {
                    traloi[i] = 0;
                }
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) questions);
                intent.putExtra("listQ", args);
//                intent.putExtra("numQ", numQ);
                intent.putExtra("traloi", traloi);
                startActivity(intent);
            }
        }
        if (v == btSelect) {
            Intent intent = new Intent(HomeActivity.this, SubjectActivity.class);
            startActivity(intent);
        }
        if (v == btEdit) {
            Intent intent = new Intent(HomeActivity.this, AddSubActivity.class);
            startActivity(intent);
        }
    }
}
package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projectand.model.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    private Button btStart;
    private EditText etTime;
    private ArrayList<Question> questions;
    private int[] traloi;
    private TextView tv;
    private int tg;
    private ImageView btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        btStart = findViewById(R.id.btTime);
        etTime = findViewById(R.id.Time);
        btReturn = findViewById(R.id.btReturn);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        int numQ = intent.getIntExtra("numQ", 10);
        traloi = intent.getIntArrayExtra("traloi");
        Bundle args = intent.getBundleExtra("listQ");
        questions = (ArrayList<Question>) args.getSerializable("ARRAYLIST");

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = etTime.getText().toString();
                if(a.equals("0")){
                    etTime.setError("Thoi gian khac 0");
                }
                else {
                    if (a.isEmpty()) {
                        tg = 1;
                    }  else {
                        tg = 2 * Integer.parseInt(a);
                    }
                    Intent intent = new Intent(TimeActivity.this, QuestionActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) questions);
                    intent.putExtra("listQ", args);
                    intent.putExtra("numQ", numQ);
                    intent.putExtra("traloi", traloi);
                    intent.putExtra("Time", tg);
                    startActivity(intent);
                }

            }
        });
    }
}
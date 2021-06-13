package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.projectand.model.Question;

import java.util.ArrayList;

public class KeyActivity extends AppCompatActivity {
    private Button btNext, btPrev, btHome;
    private TextView txtQuest;
    private RadioButton aws1, aws2, aws3, aws4;
    private int position = 1;
    private ImageView btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        initView();
        btPrev.setEnabled(false);
        Intent intent = getIntent();
        int[] traloi = intent.getIntArrayExtra("traloi");
        Bundle args = intent.getBundleExtra("listQ");
        ArrayList<Question> questions = (ArrayList<Question>) args.getSerializable("ARRAYLIST");
        int numQ = questions.size();
        if (numQ == 1) btNext.setEnabled(false);
        Question q = questions.get(position - 1);
        txtQuest.setText(q.getQues());
        aws1.setText(q.getAnswer1());
        aws2.setText(q.getAnswer2());
        aws3.setText(q.getAnswer3());
        aws4.setText(q.getAnswer4());
        int dapan = q.getCorrect();
        int check = traloi[1];
        switch (check) {
            case 1:
                aws1.setChecked(true);
                break;
            case 2:
                aws2.setChecked(true);
                break;
            case 3:
                aws3.setChecked(true);
                break;
            case 4:
                aws4.setChecked(true);
                break;
        }
        switch (dapan) {
            case 1:
                aws1.setBackgroundColor(getColor(R.color.green));
                break;
            case 2:
                aws2.setBackgroundColor(getColor(R.color.green));
                break;
            case 3:
                aws3.setBackgroundColor(getColor(R.color.green));
                break;
            case 4:
                aws4.setBackgroundColor(getColor(R.color.green));
                break;
        }

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPrev.setEnabled(true);
                position = position + 1;
                int check = traloi[position];
                aws1.setChecked(false);
                aws2.setChecked(false);
                aws3.setChecked(false);
                aws4.setChecked(false);
                aws1.setBackgroundColor(getColor(R.color.white));
                aws2.setBackgroundColor(getColor(R.color.white));
                aws3.setBackgroundColor(getColor(R.color.white));
                aws4.setBackgroundColor(getColor(R.color.white));
                switch (check) {
                    case 1:
                        aws1.setChecked(true);
                        break;
                    case 2:
                        aws2.setChecked(true);
                        break;
                    case 3:
                        aws3.setChecked(true);
                        break;
                    case 4:
                        aws4.setChecked(true);
                        break;
                }
                Question q = questions.get(position - 1);
                txtQuest.setText(q.getQues());
                aws1.setText(q.getAnswer1());
                aws2.setText(q.getAnswer2());
                aws3.setText(q.getAnswer3());
                aws4.setText(q.getAnswer4());
                int dapan = q.getCorrect();
                switch (dapan) {
                    case 1:
                        aws1.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 2:
                        aws2.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 3:
                        aws3.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 4:
                        aws4.setBackgroundColor(getColor(R.color.green));
                        break;
                }
                if (position == numQ) {
                    btNext.setEnabled(false);
                }
            }
        });
        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNext.setEnabled(true);
                position = position - 1;
                int check = traloi[position];
                aws1.setChecked(false);
                aws2.setChecked(false);
                aws3.setChecked(false);
                aws4.setChecked(false);
                aws1.setBackgroundColor(getColor(R.color.white));
                aws2.setBackgroundColor(getColor(R.color.white));
                aws3.setBackgroundColor(getColor(R.color.white));
                aws4.setBackgroundColor(getColor(R.color.white));
                switch (check) {
                    case 1:
                        aws1.setChecked(true);
                        break;
                    case 2:
                        aws2.setChecked(true);
                        break;
                    case 3:
                        aws3.setChecked(true);
                        break;
                    case 4:
                        aws4.setChecked(true);
                        break;
                }
                Question q = questions.get(position - 1);
                txtQuest.setText(q.getQues());
                aws1.setText(q.getAnswer1());
                aws2.setText(q.getAnswer2());
                aws3.setText(q.getAnswer3());
                aws4.setText(q.getAnswer4());
                int dapan = q.getCorrect();
                switch (dapan) {
                    case 1:
                        aws1.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 2:
                        aws2.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 3:
                        aws3.setBackgroundColor(getColor(R.color.green));
                        break;
                    case 4:
                        aws4.setBackgroundColor(getColor(R.color.green));
                        break;
                }
                if (position == 1) {
                    btPrev.setEnabled(false);
                }
            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KeyActivity.this, HomeActivity.class));
            }
        });

        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
        txtQuest = findViewById(R.id.txtQuest);
        aws1 = findViewById(R.id.answer1);
        aws2 = findViewById(R.id.answer2);
        aws3 = findViewById(R.id.answer3);
        aws4 = findViewById(R.id.answer4);
        btHome = findViewById(R.id.btHome);
        btReturn = findViewById(R.id.btReturn);
    }
}
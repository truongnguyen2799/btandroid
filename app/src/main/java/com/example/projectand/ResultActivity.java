package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projectand.model.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView txtChua, txtSai, txtDung, txtDiem;
    private ProgressBar pgbChua, pgbSai, pgbDung;
    private Button btMain, btKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        Intent intent = getIntent();
        int[] traloi = intent.getIntArrayExtra("traloi");
        Bundle args = intent.getBundleExtra("listQ");
        double chualam = 0.00;
        double sai = 0.00;
        double dung = 0.00;
        ArrayList<Question> questions = (ArrayList<Question>) args.getSerializable("ARRAYLIST");
        int numQ = questions.size();
        for (int i = 0; i < (questions.size()); i++) {
            Question q = questions.get(i);
            int dapan = q.getCorrect();
            if (traloi[i + 1] == 0) chualam++;
            else if (traloi[i + 1] == dapan) dung++;
            else sai++;
        }
        txtDung.setText("Đúng: " + (int) dung);
        txtSai.setText("Sai: " + (int) sai);
        txtChua.setText("Chưa làm: " + (int) chualam);
        pgbDung.setProgress((int) ((dung / numQ) * 100));
        pgbSai.setProgress((int) ((sai / numQ) * 100));
        pgbChua.setProgress((int) ((chualam / numQ) * 100));
        double diem = dung * 10 / numQ;
        diem = Math.ceil(diem*100.0)/100.0;
        txtDiem.setText("" + diem);

        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, HomeActivity.class));
            }
        });

        btKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, KeyActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) questions);
                intent.putExtra("listQ", args);
                intent.putExtra("traloi", traloi);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        txtChua = findViewById(R.id.txtChualam);
        txtSai = findViewById(R.id.txtSai);
        txtDung = findViewById(R.id.txtDung);
        txtDiem = findViewById(R.id.txtDiem);
        pgbChua = findViewById(R.id.pgbChualam);
        pgbSai = findViewById(R.id.pgbSai);
        pgbDung = findViewById(R.id.pgbDung);
        btMain = findViewById(R.id.btMain);
        btKey = findViewById(R.id.btKey);
    }

}
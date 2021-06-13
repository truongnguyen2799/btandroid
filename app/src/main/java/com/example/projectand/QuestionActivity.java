package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.projectand.model.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {
    private Button btNext, btPrev, btSubmit, btExit;
    private TextView txtQuest, txtTime;
    private RadioButton aws1, aws2, aws3, aws4;
    private int position = 1;
    private ArrayList<Question> questions;
    private int[] traloi;
    public static CountDownTimer timer;
    private int time = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
        btPrev.setEnabled(false);
        Intent intent = getIntent();
        time = intent.getIntExtra("Time",1);
//        int numQ = intent.getIntExtra("numQ", 10);
        traloi = intent.getIntArrayExtra("traloi");
        Bundle args = intent.getBundleExtra("listQ");
        questions = (ArrayList<Question>) args.getSerializable("ARRAYLIST");
        int numQ =questions.size();
        Question q = questions.get(position - 1);
        txtQuest.setText("Câu 1: "+q.getQues());
        aws1.setText(q.getAnswer1());
        aws2.setText(q.getAnswer2());
        aws3.setText(q.getAnswer3());
        aws4.setText(q.getAnswer4());

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPrev.setEnabled(true);
                int ans = 0;
                if (aws1.isChecked()) {
                    ans = 1;
                }
                if (aws2.isChecked()) {
                    ans = 2;
                }
                if (aws3.isChecked()) {
                    ans = 3;
                }
                if (aws4.isChecked()) {
                    ans = 4;
                }
                traloi[position] = ans;
                position = position + 1;
                int check = traloi[position];
                switch (check) {
                    case 0:
                        aws1.setChecked(false);
                        aws2.setChecked(false);
                        aws3.setChecked(false);
                        aws4.setChecked(false);
                        break;
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
                txtQuest.setText("Câu "+position+": "+q.getQues());
                aws1.setText(q.getAnswer1());
                aws2.setText(q.getAnswer2());
                aws3.setText(q.getAnswer3());
                aws4.setText(q.getAnswer4());
                if (position == numQ) {
                    btNext.setEnabled(false);
                }
            }
        });
        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNext.setEnabled(true);
                int ans = 0;
                if (aws1.isChecked()) {
                    ans = 1;
                }
                if (aws2.isChecked()) {
                    ans = 2;
                }
                if (aws3.isChecked()) {
                    ans = 3;
                }
                if (aws4.isChecked()) {
                    ans = 4;
                }
                traloi[position] = ans;
                position = position - 1;
                int check = traloi[position];
                switch (check) {
                    case 0:
                        aws1.setChecked(false);
                        aws2.setChecked(false);
                        aws3.setChecked(false);
                        aws4.setChecked(false);
                        break;
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
                txtQuest.setText("Câu "+position+": "+q.getQues());
                aws1.setText(q.getAnswer1());
                aws2.setText(q.getAnswer2());
                aws3.setText(q.getAnswer3());
                aws4.setText(q.getAnswer4());
                if (position == 1) {
                    btPrev.setEnabled(false);
                }
            }
        });

        timer = new CouterClass(time * 60 * 500, 1000);
        timer.start();
    }

    public void exit(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Exit");
        b.setMessage("Bạn có chắc chắn muốn hủy bài đang làm?");
        b.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
                startActivity(new Intent(QuestionActivity.this, HomeActivity.class));
            }
        });
        b.create().show();
    }

    public void submit(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Submit");
        b.setMessage("Bạn chắc chắn nộp bài?");
        b.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int ans = 0;
                if (aws1.isChecked()) {
                    ans = 1;
                }
                if (aws2.isChecked()) {
                    ans = 2;
                }
                if (aws3.isChecked()) {
                    ans = 3;
                }
                if (aws4.isChecked()) {
                    ans = 4;
                }
                traloi[position] = ans;
                Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) questions);
                intent.putExtra("listQ", args);
                intent.putExtra("traloi", traloi);
                startActivity(intent);
                timer.cancel();
            }
        });
        b.create().show();
    }

    public class CouterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CouterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            txtTime.setText(countTime);
        }

        @Override
        public void onFinish() {
            txtTime.setText("Hết giờ!");
            int ans = 0;
            if (aws1.isChecked()) {
                ans = 1;
            }
            if (aws2.isChecked()) {
                ans = 2;
            }
            if (aws3.isChecked()) {
                ans = 3;
            }
            if (aws4.isChecked()) {
                ans = 4;
            }
            traloi[position] = ans;
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) questions);
            intent.putExtra("listQ", args);
            intent.putExtra("traloi", traloi);
            startActivity(intent);
        }
    }

    private void initView() {
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
        btSubmit = findViewById(R.id.btSubmit);
        txtQuest = findViewById(R.id.txtQuest);
        txtTime = findViewById(R.id.txtTime);
        aws1 = findViewById(R.id.answer1);
        aws2 = findViewById(R.id.answer2);
        aws3 = findViewById(R.id.answer3);
        aws4 = findViewById(R.id.answer4);
        btExit = findViewById(R.id.Exit);
    }
}
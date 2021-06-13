package com.example.projectand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.projectand.db.Database;
import com.example.projectand.model.Question;

public class AddQuesActivity extends AppCompatActivity {
    private EditText etQues, etAns1, etAns2, etAns3, etAns4;
    private RadioButton ans1, ans2, ans3, ans4;
    private Button btAdd;
    private ImageView btReturn;
    private Database db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ques);
        initView();
        Intent intent = getIntent();
        int idSub = intent.getIntExtra("idSub",1);
        db = new Database(this);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ques = etQues.getText().toString();
                String da1 = etAns1.getText().toString();
                String da2 = etAns2.getText().toString();
                String da3 = etAns3.getText().toString();
                String da4 = etAns4.getText().toString();
                int dapan =1;
                if(ans1.isChecked())    dapan =1;
                if(ans2.isChecked())    dapan =2;
                if(ans3.isChecked())    dapan =3;
                if(ans4.isChecked())    dapan =4;
                Question question = new Question(ques,da1,da2,da3,da4,dapan,idSub);
                db.addQues(question);
                finish();
            }
        });
    }

    private void initView() {
        etQues = findViewById(R.id.etQues);
        etAns1 = findViewById(R.id.etAns1);
        etAns2 = findViewById(R.id.etAns2);
        etAns3 = findViewById(R.id.etAns3);
        etAns4 = findViewById(R.id.etAns4);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        btAdd = findViewById(R.id.btAdd);
        btReturn = findViewById(R.id.btReturn);
    }
}
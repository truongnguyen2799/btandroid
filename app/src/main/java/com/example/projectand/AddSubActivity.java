package com.example.projectand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projectand.Adapter.AddSubAdapter;
import com.example.projectand.db.Database;
import com.example.projectand.model.Subject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recSub;
    private FloatingActionButton fabAddSub;
    private ImageView btReturn;
    private Database db;
    private AddSubAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        initView();
        db = new Database(this);
        recSub.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new AddSubAdapter(this);
        List<Subject> subjects = db.getAllSubject();
        adapter.setSubjects(subjects);
        recSub.setAdapter(adapter);
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabAddSub.setOnClickListener(this);
    }

    private void initView() {
        recSub = findViewById(R.id.recAddSub);
        fabAddSub = findViewById(R.id.fabAddSub);
        btReturn = findViewById(R.id.btReturn);
    }

    @Override
    protected void onRestart() {
        List<Subject> subjects = db.getAllSubject();
        adapter.setSubjects(subjects);
        recSub.setAdapter(adapter);
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        if (v == fabAddSub) {
            final EditText etSubName;
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater	= this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialogaddsub,null);
            etSubName = dialogView.findViewById(R.id.etSubName);
            dialogBuilder.setTitle("Thêm mới");
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialogBuilder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String subName = etSubName.getText().toString();
                    Subject s = new Subject(subName);
                    db.addSub(s);
                    onRestart();
                }
            });
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        }
    }
}
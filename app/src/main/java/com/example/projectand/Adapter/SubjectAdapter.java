package com.example.projectand.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectand.QuestionActivity;
import com.example.projectand.R;
import com.example.projectand.TimeActivity;
import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private List<Subject> list;
    private Activity activity;
    private Database db;

    public SubjectAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
        db = new Database(activity);
    }

    public void setSubjects(List<Subject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.subject_card, parent, false);
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
        Subject s = list.get(position);
        List<Question> questions = db.getBySub(s.getSubjectId());
        int numQ = questions.size();
        if(numQ>0){
            holder.txtSubName.setText(s.getName()+"("+numQ+" câu)");
        }else {
            holder.txtSubName.setText(s.getName());
        }

//        Activity activity = this.activity;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questions.isEmpty()) {
                    Toast.makeText(activity, "Chủ đề chưa có câu hỏi", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(activity, TimeActivity.class);
                    int[] traloi = new int[numQ + 1];
                    for (int i = 0; i <= (numQ); i++) {
                        traloi[i] = 0;
                    }
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) questions);
                    intent.putExtra("listQ", args);
//                    intent.putExtra("numQ", numQ);
                    intent.putExtra("traloi", traloi);
                    activity.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSubName;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubName = itemView.findViewById(R.id.txtNameSub);

        }
    }
}

package com.example.projectand.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectand.EditQuesActivity;
import com.example.projectand.R;
import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.QuesViewHolder> {
    private List<Question> list;
    private Activity activity;
    private Database db;

    public QuesAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
        db = new Database(activity);
    }

    public void setQues(List<Question> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.subject_card, parent, false);
        return new QuesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuesViewHolder holder, int position) {
        Question question = list.get(position);
        holder.txtQues.setText(question.getQues());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditQuesActivity.class);
                intent.putExtra("idQues",question.getId());
                activity.startActivity(intent);
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

    public class QuesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtQues;

        public QuesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQues = itemView.findViewById(R.id.txtNameSub);
        }
    }
}

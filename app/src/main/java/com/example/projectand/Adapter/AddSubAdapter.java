package com.example.projectand.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectand.ListQuesActivity;
import com.example.projectand.R;
import com.example.projectand.db.Database;
import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class AddSubAdapter extends RecyclerView.Adapter<AddSubAdapter.AddSubViewHolder> {
    private List<Subject> list;
    private Activity activity;
    private Database db;

    public AddSubAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
        db = new Database(activity);
    }

    public void setSubjects(List<Subject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AddSubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.subject_card, parent, false);
        return new AddSubViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddSubViewHolder holder, int position) {
        Subject s = list.get(position);
        List<Question> questions = db.getBySub(s.getSubjectId());
        holder.txtSubName.setText(s.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity , ListQuesActivity.class);
                int idSub = s.getSubjectId();
                intent.putExtra("idSub", idSub);
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

    public class AddSubViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSubName;

        public AddSubViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubName = itemView.findViewById(R.id.txtNameSub);
        }
    }
}

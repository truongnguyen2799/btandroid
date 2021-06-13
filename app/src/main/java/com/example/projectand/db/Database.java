package com.example.projectand.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projectand.model.Question;
import com.example.projectand.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Tracnghiem1.db";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "create table subject(" +
                "id integer primary key autoincrement," +
                "name text" +
                ");";
        db.execSQL(sql1);
        String addSub = "INSERT INTO subject(id,name)" +
                "VALUES(1,'Toán' )," +
                "(2,'Lịch sử')," +
                "(3,'Địa lí')," +
                "(4,'Văn học')," +
                "(5,'GDCD');";
        db.execSQL(addSub);
        String sql2 = "create table question(" +
                "id integer primary key autoincrement," +
                "ques text," +
                "answer1 text," +
                "answer2 text," +
                "answer3 text," +
                "answer4 text," +
                "correct integer," +
                "subjectId integer" +
                ");";
        db.execSQL(sql2);
        String addQues = "INSERT INTO question(id,ques,answer1,answer2,answer3,answer4,correct,subjectId) " +
                "VALUES(1, 'Kết quả của 3 + 5 là:','3','5','7','8',4,1)," +
                "(2, 'Kết quả của 8/4 là:','1','2','3','4',2,1)," +
                "(3, 'Trong các số từ 0 đến 10, số lớn nhất là số:','0','1','9','10',4,1)," +
                "(4,'Ai là người đặt tên nước ta là Vạn Xuân?','Lí Bạch','Lý Bôn','Ngô Quyền','Lý Công Uẩn',2,2)," +
                "(5, 'Tên thật của nữ vương đầu tiên và cuối cùng của nước ta','Lý Phật Thiên','Trưng Trắc','Lý Phật Kim','Trưng Nhị',3,2)," +
                "(6,'Có bao nhiêu đời vua vào thời Lý?','6','7','8','9',4,2)," +
                "(7, 'Đâu là 1 tên khác của Trần Quốc Tuấn?','Trần Hưng Đạo','Trần Quốc Toản','Trần Khánh Dư','Trần Nhật Duật',1,2)," +
                "(8, 'Đối với xã hội, sản xuất vật chất đóng vai trò là','Cơ sở tồn tại và phát triển','Động lực phát triển','Thước đo phát triển','Cơ sở tồn tại',1,5)," +
                "(9, 'Yếu tố nào không phải là yếu tố cơ bản của quá trình sản xuất?','Sức lao động','Đối tượng lao động','Tư liệu lao động','Lao động',4,5)," +
                "(10, 'Tổng thư ký Liên Hợp Quốc từ năm 2017 là người nước nào?','Tây ban Nha','Hàn Quốc','Bồ Đào Nha','Canada',3,2)";
        db.execSQL(addQues);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addQues(Question question) {
        ContentValues values = new ContentValues();
        values.put("ques", question.getQues());
        values.put("answer1", question.getAnswer1());
        values.put("answer2", question.getAnswer2());
        values.put("answer3", question.getAnswer3());
        values.put("answer4", question.getAnswer4());
        values.put("correct", question.getCorrect());
        values.put("subjectId", question.getSubject_id());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("question", null, values);
    }


    public List<Question> getAllQues() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("question", null, null, null, null, null, null);
        List<Question> questions = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ques = cursor.getString(1);
            String answer1 = cursor.getString(2);
            String answer2 = cursor.getString(3);
            String answer3 = cursor.getString(4);
            String answer4 = cursor.getString(5);
            int correct = cursor.getInt(6);
            int subjectId = cursor.getInt(7);
            Question question = new Question(id, ques, answer1, answer2, answer3, answer4, correct, subjectId);
            questions.add(question);
        }
        return questions;
    }

    public List<Question> getBySub(int subId) {
        String sql = "select *from question where subjectId = ?";
        String[] args = {String.valueOf(subId)};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        List<Question> questions = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ques = cursor.getString(1);
            String answer1 = cursor.getString(2);
            String answer2 = cursor.getString(3);
            String answer3 = cursor.getString(4);
            String answer4 = cursor.getString(5);
            int correct = cursor.getInt(6);
            int subjectId = cursor.getInt(7);
            Question question = new Question(id, ques, answer1, answer2, answer3, answer4, correct, subjectId);
            questions.add(question);
        }
        return questions;
    }

    public Question getQuesById(int id) {
        String sql = "select * from question where id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        if (cursor.moveToNext()) {
            String ques = cursor.getString(1);
            String answer1 = cursor.getString(2);
            String answer2 = cursor.getString(3);
            String answer3 = cursor.getString(4);
            String answer4 = cursor.getString(5);
            int correct = cursor.getInt(6);
            int subjectId = cursor.getInt(7);
            Question question = new Question(id, ques, answer1, answer2, answer3, answer4, correct, subjectId);
            return question;
        }
        return null;
    }

    public int updateQues(Question question) {
        String whereClause = "id = ?";
        String[] args = {String.valueOf(question.getId())};
        ContentValues values = new ContentValues();
        values.put("ques", question.getQues());
        values.put("answer1", question.getAnswer1());
        values.put("answer2", question.getAnswer2());
        values.put("answer3", question.getAnswer3());
        values.put("answer4", question.getAnswer4());
        values.put("correct", question.getCorrect());
        values.put("subjectId", question.getSubject_id());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("question", values, whereClause, args);
    }

    public int deleteQues(int id) {
        String whereClause = "id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("question", whereClause, args);
    }

    public long addSub(Subject subject) {
        ContentValues values = new ContentValues();
        values.put("name", subject.getName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("subject", null, values);
    }

    public int updateSub(Subject subject) {
        String whereClause = "id = ?";
        String[] args = {String.valueOf(subject.getSubjectId())};
        ContentValues values = new ContentValues();
        values.put("name", subject.getName());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("subject", values, whereClause, args);
    }

    public int deleteSub(int id) {
        String whereClause = "id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("subject", whereClause, args);
    }

    public List<Subject> getAllSubject() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("subject", null, null, null, null, null, null);
        List<Subject> subjects = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Subject subject = new Subject(id, name);
            subjects.add(subject);
        }
        return subjects;
    }
}

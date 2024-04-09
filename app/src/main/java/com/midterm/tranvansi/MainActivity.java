package com.midterm.tranvansi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.midterm.tranvansi.Database.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private TextView textViewQuestion;
    private List<String> questionList;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        textViewQuestion = findViewById(R.id.textView);

        questionList = databaseHelper.getAllQuestions();

        currentQuestionIndex = 0;

        displayQuestion();

        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addQuestion("Question 1");
                databaseHelper.addQuestion("Question 2");
                databaseHelper.addQuestion("Question 3");

                String latestQuestion = databaseHelper.getLatestQuestion();
                textViewQuestion.setText(latestQuestion);
            }
        });
        Button btnRight = findViewById(R.id.btn_right);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Di chuyển đến câu hỏi tiếp theo
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                }
            }
        });
        Button btnLeft = findViewById(R.id.btn_left);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Di chuyển đến câu hỏi trước đó
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion();
                }
            }
        });
    }
    private void displayQuestion() {
        // Lấy câu hỏi từ danh sách dựa vào chỉ mục hiện tại và hiển thị nó
        String question = questionList.get(currentQuestionIndex);
        textViewQuestion.setText(question);
    }
}
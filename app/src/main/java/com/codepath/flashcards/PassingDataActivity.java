package com.codepath.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PassingDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_data);

        final TextView question = findViewById(R.id.question);
        final TextView answer = findViewById(R.id.answer);
//        final TextView choice1 = findViewById(R.id.choice1);
//        final TextView choice2 = findViewById(R.id.choice2);
//        final TextView choice3 = findViewById(R.id.choice3);

        question.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                question.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.VISIBLE);
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answer.setVisibility(View.INVISIBLE);
                question.setVisibility(View.VISIBLE);
            }
        });
    }
}

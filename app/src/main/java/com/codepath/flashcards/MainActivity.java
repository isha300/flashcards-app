package com.codepath.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView questionText = findViewById(R.id.flashcard_question);
        final TextView answerText = findViewById(R.id.flashcard_answer);

        questionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionText.setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        answerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.VISIBLE);
            }
        });
    }
}

package com.codepath.flashcards;

import android.content.Intent;
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
//        final TextView choice1 = findViewById(R.id.choice1);
//        final TextView choice2 = findViewById(R.id.choice2);
//        final TextView choice3 = findViewById(R.id.choice3);

        questionText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                questionText.setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        answerText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.VISIBLE);
            }
        });

//        choice1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                choice1.setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
//                choice3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
//            }
//        });
//
//        choice2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                choice2.setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
//                choice3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
//            }
//        });
//
//        choice3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                choice3.setBackgroundColor(getResources().getColor(R.color.my_green_color, null));
//            }
//        });

        findViewById(R.id.myBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 100 && data != null) {
//            String string1 = data.getExtras().getString("string1");
//            String string2 = data.getExtras().getString("string2");
//        }
//    }
}

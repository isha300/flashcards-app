package com.codepath.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView questionText;
    TextView answerText;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.flashcard_question);
        answerText = findViewById(R.id.flashcard_answer);
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

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

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && data != null) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            questionText.setText(string1);
            answerText.setText(string2);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
        }
    }
}

package com.codepath.flashcards;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        // get the center for the clipping circle
                int cx = answerText.getWidth() / 2;
                int cy = answerText.getHeight() / 2;

        // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerText, cx, cy, 0f, finalRadius);

        // hide the question and show the answer to prepare for playing the animation!
                questionText.setVisibility(View.INVISIBLE);
                answerText.setVisibility(View.VISIBLE);

                anim.setDuration(1000);
                anim.start();
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                        // advance our pointer index so we can show the next card
                        currentCardDisplayedIndex++;

                        // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                        if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                            currentCardDisplayedIndex = 0;
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // set the question and answer TextViews with data from the database
                        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

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

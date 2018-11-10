package com.codepath.flashcards;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.logging.Logger;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.myBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.myBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new Intent, this is where we will put our data
                Intent data = new Intent();
                String quesString = ((EditText) findViewById(R.id.enter_question)).getText().toString();
                String aString = ((EditText) findViewById(R.id.enter_answer)).getText().toString();
                data.putExtra("string1", quesString); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", aString); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();
            }
        });

    }
}

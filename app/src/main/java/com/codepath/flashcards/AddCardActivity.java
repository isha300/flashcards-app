package com.codepath.flashcards;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
                Intent intent = new Intent(AddCardActivity.this, PassingDataActivity.class);
                AddCardActivity.this.startActivityForResult(intent, 100);
//                ((EditText) findViewById(R.id.enter_question)).getText().toString();
//
//                Intent data = new Intent(); // create a new Intent, this is where we will put our data
//                data.putExtra("string1", "Who is the 44th President of the United States?"); // puts one string into the Intent, with the key as 'string1'
//                data.putExtra("string2", "Barack Obama"); // puts another string into the Intent, with the key as 'string2
//                setResult(RESULT_OK, data); // set result code and bundle data for response
//                finish();
            }
        });

    }
}

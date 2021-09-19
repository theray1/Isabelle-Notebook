package com.example.isabelletournamentpocketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DataDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        Intent intent = getIntent();
        Character currentCharacter = (Character)intent.getSerializableExtra("character");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fieldcontainer);

        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView name =(TextView) findViewById(R.id.Name);
        name.setText(currentCharacter.getName());

        for( int i = 2; i < currentCharacter.getFields().length; i++ )
        {
            TextView textView = new TextView(this);
            textView.setText(currentCharacter.getFields()[i] + " : " + currentCharacter.getData()[i]);
            linearLayout.addView(textView);
        }




        Button backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataDisplayActivity.this.finish();
            }
        });
    }
}
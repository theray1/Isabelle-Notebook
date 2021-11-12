package com.example.isabelletournamentpocketapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String[] arrayOfRawFieldsAndRawCharacters;
    private Spreadsheet sheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
        loadingDialog.startLoadingAnimation();

        textSearchListenerInitializer();
        buttonListenerInitializer();

        SpreadsheetTask spreadsheetTask = new SpreadsheetTask() {
            @Override
            protected Object doInBackground(MainActivity... mainActivities) {
                Spreadsheet sheet = new Spreadsheet(MainActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                loadingDialog.dismissDialog();
            }
        };
        spreadsheetTask.execute();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    View.OnClickListener buttonSelctionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            String characterNameAndId = view.getResources().getResourceEntryName(id);

            String characterId = characterNameAndId.replaceAll("[a-z]","");

            Character character = new Character(arrayOfRawFieldsAndRawCharacters, characterId);

            startDataDisplayActivity(character);
        }
    };

    private void startDataDisplayActivity(Character character){
        Toast.makeText(MainActivity.this, character.getName(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MainActivity.this, DataDisplayActivity.class);
        i.putExtra("character", character);

        startActivity(new Intent(i));
    }

    protected void textSearchListenerInitializer() {
        EditText textSearch = (EditText) findViewById(R.id.textInput);
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println(textSearch.getText().toString());
                int id;
                String returnID;
                ViewGroup group = (ViewGroup)findViewById(R.id.buttongrid);
                View v;
                for(int buttonIterator = 0; buttonIterator < group.getChildCount(); buttonIterator++) {
                    v = group.getChildAt(buttonIterator);
                    id = v.getId();

                    returnID = v.getResources().getResourceEntryName(id);

                    if(!returnID.toLowerCase().contains(textSearch.getText().toString().toLowerCase())){
                        v.setVisibility(View.GONE);
                    } else{
                        v.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void buttonListenerInitializer(){
        ViewGroup group = (ViewGroup)findViewById(R.id.buttongrid);
        View v;
        for(int i = 0; i < group.getChildCount(); i++) {
            v = group.getChildAt(i);
            if(v instanceof ImageButton) v.setOnClickListener(buttonSelctionListener);
        }
    }

    public String[] getArrayOfRawFieldsAndRawCharacters() {
        return arrayOfRawFieldsAndRawCharacters;
    }

    public Spreadsheet getSheet() {
        return sheet;
    }

    public void setArrayOfRawCharacterData(String[] arrayOfRawFieldsAndRawCharacters) {
        this.arrayOfRawFieldsAndRawCharacters = arrayOfRawFieldsAndRawCharacters;
    }
}